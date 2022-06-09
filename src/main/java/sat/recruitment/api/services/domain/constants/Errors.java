package sat.recruitment.api.services.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Errors {
    USER_DUPLICATE("001", "User is duplicated");
    private String code;
    private String description;
}
