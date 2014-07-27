package com.easywed.model;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/27/14
 * Time: 1:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class ServicePackage implements Serializable {

	private static final long serialVersionUID = -6892766222546721100L;

	public ServicePackage(){}

	public ServicePackage(Date startDate, Date endDate, Integer price) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}

	@Getter @Setter protected Date startDate;
	@Getter @Setter protected Date endDate;
	@Getter @Setter protected Integer price;

}
