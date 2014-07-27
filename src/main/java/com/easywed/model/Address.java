package com.easywed.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/27/14
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = -6892766222546721100L;

	public Address(){}

	public Address(String street1, String street2, String city, String state, String zip, String country) {
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	@Getter @Setter protected String street1;
	@Getter @Setter protected String street2;
	@Getter @Setter protected String city;
	@Getter @Setter protected String state;
	@Getter @Setter protected String zip;
	@Getter @Setter protected String country;

}
