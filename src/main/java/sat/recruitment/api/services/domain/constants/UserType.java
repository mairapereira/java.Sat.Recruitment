package sat.recruitment.api.services.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserType {
    NORMAL("Normal", "Usuario del plan estándar"),
    SUPER("SuperUser", "Usuario del superior"),
    PREMIUM("Premium", "Usuario del plan premium");

    private String code;
    private String description;
    public static UserType getFromCode(String code) {
        return Arrays.stream(UserType.values())
                .filter(type -> type.getCode().equals(code))
                .findAny()
                .orElse(null);
    }

}
