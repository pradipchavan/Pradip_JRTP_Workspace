package com.pradip.dto;

import lombok.Data;

@Data
public class ResetPwdDTO {
	
	private String email;

	private String newPassword;
	
	private String confirmPassword;
}
