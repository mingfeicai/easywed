package com.easywed.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/27/14
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class GenericModel<T> implements Serializable, Comparable<GenericModel<T>>{

	private static final long serialVersionUID = -2281646965293626220L;

	public GenericModel() {}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericModel<T> other = (GenericModel<T>) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	/**
	 * Saves typing in SPEL expressions.
	 * @return getId().toString()
	 */
	public String idString() {
		return id.toString();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + id +"]";
	}

	@Override
	public int compareTo(GenericModel<T> o) {
		if(o == null || o.getId() == null) {
			return 1;
		} else if(this.getId() == null) {
			return -1;
		}

		return this.getId().compareTo(o.getId());
	}
}
