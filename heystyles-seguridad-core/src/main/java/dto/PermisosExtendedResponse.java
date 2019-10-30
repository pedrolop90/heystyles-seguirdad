package dto;

import com.heystyles.common.types.ListResponse;
import domain.PermisoAuth0Extended;

import java.util.List;

public class PermisosExtendedResponse extends ListResponse<PermisoAuth0Extended> {

    public PermisosExtendedResponse() {
        super();
    }

    public PermisosExtendedResponse(List<PermisoAuth0Extended> permisos) {
        super(permisos);

    }

}
