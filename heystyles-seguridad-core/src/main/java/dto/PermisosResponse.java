package dto;

import com.heystyles.common.types.ListResponse;
import domain.PermisoAuth0;

import java.util.List;

public class PermisosResponse extends ListResponse<PermisoAuth0> {

    public PermisosResponse() {
        super();
    }

    public PermisosResponse(List<PermisoAuth0> permisos) {
        super(permisos);

    }

}
