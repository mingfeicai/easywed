package com.easywed.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/26/14
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class HibernateDaoImpl<T> {
	@Autowired @Getter private SessionFactory sessionFactory;

	private Class<T> persistentClass;
	private String modelType;

	public HibernateDaoImpl() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.modelType = this.persistentClass.getSimpleName();
		}
	}

	public List<T> list(Query q) {
		List<T> list = q.list();
		return list;
	}

	// some SQL queries need this
	protected String getTableName() {
		String tableName = null;
		Table table = persistentClass.getAnnotation(Table.class);
		if (table == null) {
			tableName = modelType;
		} else {
			tableName = table.name();
		}
		return tableName;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T findById(Long id) {
		T t = (T) getSession().get(persistentClass, id);
		return t;
	}

	public List<T> findByIds(Collection<Long> collection) {
		if (collection.isEmpty())
			return new ArrayList<T>();

		return createQuery("from " +  modelType + " where id in (:ids)")
				.setParameterList("ids", collection).list();
	}

	public List<T> findByIdsWithLock(Collection<Long> collection) {
		if (collection.isEmpty())
			return new ArrayList<T>();

		return createQuery("from " + modelType + " where id in (:ids)")
				.setParameterList("ids", collection)
				.setLockOptions(LockOptions.UPGRADE)
				.list();
	}


	public Long findCount(String str, Object... arg) {
		return (Long) DataAccessUtils.uniqueResult(find(str, arg));
	}

	public Long findCustomCount(final String selectClauseStr, final String str, final Object... args) {

		Query query = createQuery("select " + selectClauseStr + " " + str);
		int index = 0;
		for (Object arg : args) {
			query.setParameter(index, arg);
			index++;
		}
		return ((Long) query.uniqueResult());
	}

	@SuppressWarnings("rawtypes")
	public List find(String str, Object... args) {
		List list = findWithLimit(str, -1, args);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List findWithLimit(String str, int limit, Object... args) {
		Query queryObject = getSession().createQuery(str);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				queryObject.setParameter(i, args[i]);
			}
		}
		if (limit > 0) {
			queryObject.setMaxResults(limit);
		}
		return queryObject.list();
	}

	public List<T> findAll() {
		List<T> all = findAll(persistentClass);
		return all;
	}

	public List<T> findAll(Class<T> clazz) {
		return getSession().createQuery("from " + clazz.getSimpleName()).list();
	}

	public T findOne(String str, Object... args) {
		T entity = DataAccessUtils.uniqueResult((List<T>)findWithLimit(str, 1, args));
		return entity;
	}

	@Transactional
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void refresh(T entity) {
		getSession().refresh(entity);
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void saveAll(Collection<T> entities) {
		for(T entity: entities) {
			save(entity);
		}
	}

	public T load(Long id) {
		return (T) getSession().load(modelType, id);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public T merge(T entity) {
		return (T) getSession().merge(entity);
	}

	public void updateAll(List<T> entity) {
		Session tmp = getSession();
		for (T t : entity) {
			tmp.merge(t);
		}
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Criteria getCriteria(boolean cacheable, int limit) {
		return getSession()
				.createCriteria(persistentClass)
				.setCacheable(cacheable)
				.setMaxResults(limit);
	}

	public Query createQuery(String query) {
		return getSession().createQuery(query);
	}

	public SQLQuery createSQLQuery(String query) {
		return getSession().createSQLQuery(query);
	}

	public void flush() {
		getSession().flush();
	}

	public List<Long> getIds(Long fromId, Long toId){
		return getSession().createCriteria(persistentClass)
				.setProjection(Projections.property("id"))
				.add(Restrictions.ge("id", fromId))
				.add(Restrictions.le("id", toId))
				.list();
	}
}
