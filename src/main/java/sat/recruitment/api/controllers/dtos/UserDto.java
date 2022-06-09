package sat.recruitment.api.controllers.dtos;

import lombok.Data;

@Data
public class UserDto {
	private String name;
	private String email;
	private String address;
	private String phone;
	private String userType;
	private Double money;
}
