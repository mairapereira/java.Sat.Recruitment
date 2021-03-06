package sat.recruitment.api.services.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sat.recruitment.api.services.domain.constants.UserType;

@Getter
@Setter
@SuperBuilder
public abstract class User {
	private String name;
	private String email;
	private String address;
	private String phone;
	private UserType userType;
	private Double money;

	public abstract void chargeGift();
}