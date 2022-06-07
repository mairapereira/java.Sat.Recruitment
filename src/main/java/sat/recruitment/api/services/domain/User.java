package sat.recruitment.api.services.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private String name;
	private String email;
	private String address;
	private String phone;
	private String userType;
	private Double money;
}