package in.pradip.dto;

import lombok.Data;

@Data
public class ResetPwdDto {

    private String email;

    private String oldPasswd;

    private String newPassword;

    private String confirmPasswd;
}
