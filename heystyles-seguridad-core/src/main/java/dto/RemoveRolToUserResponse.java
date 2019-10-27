package dto;

import com.heystyles.common.types.ObjectResponse;
import domain.EstadoUser;

public class RemoveRolToUserResponse extends ObjectResponse<EstadoUser> {
    public RemoveRolToUserResponse() {
        super();
    }

    public RemoveRolToUserResponse(EstadoUser estadoUser) {
        super(estadoUser);
    }
}
