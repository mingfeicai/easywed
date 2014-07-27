package com.easywed.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/26/14
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Member extends GenericModel<Member> {

	@Setter @Getter private String name;
	@Setter @Getter private String des;

}
