package com.pradip.dto;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
public class CustomerDTO {
	
	private Integer customerId;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Long phoneNo;
	
	private String pwdUpdated;

	private String dateCreated;

	private String lastUpdated;
}
