package domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.heystyles.common.exception.InvalidEnumValueException;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EstadoUser {

    REMOVIDO_ROL, USER_ELIMINADO;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static EstadoUser fromValue(String value) {
        if (value != null && value.isEmpty()) {
            return null;
        }
        for (EstadoUser p : values()) {
            if (p.name().equals(value)) {
                return p;
            }
        }
        throw new InvalidEnumValueException("estadoRemoveRolToUser", value);
    }

}
