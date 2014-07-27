package com.easywed.model;


import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/27/14
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Venue extends Vendor {
	@Getter @Setter private Integer minNumOfGuest;
	@Getter @Setter private Integer maxNumOfGuest;
}
