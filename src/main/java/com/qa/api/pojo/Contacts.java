package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contacts{
	private String firstName;
	private String lastName;
	private String country;
	private String birthdate;
	private String phone;
	private String city;
	private String postalCode;
	private String stateProvince;
	private String street1;
	private String street2;
	private String email;
}