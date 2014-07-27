package com.easywed.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/27/14
 * Time: 12:57 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Vendor extends GenericModel<Vendor> {

	@Getter @Setter private String name;
	@Getter @Setter private String description;
	@Getter @Setter private Address address;
	@Getter @Setter private Contact contact;

}
