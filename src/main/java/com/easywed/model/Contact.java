package com.easywed.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/27/14
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class Contact implements Serializable {

	private static final long serialVersionUID = -6892766222546721100L;

	public Contact(){}

	public Contact(String contactName, String phone, String email, String fax) {
		this.contactName = contactName;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
	}

	@Getter @Setter protected String contactName;
	@Getter @Setter protected String phone;
	@Getter @Setter protected String email;
	@Getter @Setter protected String fax;
}
