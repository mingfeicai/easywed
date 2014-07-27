package com.easywed.dao;

import org.springframework.stereotype.Repository;

import com.easywed.model.Member;


@Repository
public class MemberDaoImpl extends HibernateDaoImpl<Member> implements MemberDao{


}
