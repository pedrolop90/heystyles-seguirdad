package domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.heystyles.common.exception.InvalidEnumValueException;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoAccionPermiso {

    LEER, INSERTAR, EDITAR, ELIMINAR;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static TipoAccionPermiso fromValue(String value) {
        if (value != null && value.isEmpty()) {
            return null;
        }
        for (TipoAccionPermiso p : values()) {
            if (p.name().equals(value)) {
                return p;
            }
        }
        throw new InvalidEnumValueException("tipoAccionPermiso", value);
    }
}
