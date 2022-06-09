package sat.recruitment.api.dao.model;

import lombok.Data;

@Data
public class UserDbo {
	private String name;
	private String email;
	private String address;
	private String phone;
	private String userType;
	private Double money;
}
