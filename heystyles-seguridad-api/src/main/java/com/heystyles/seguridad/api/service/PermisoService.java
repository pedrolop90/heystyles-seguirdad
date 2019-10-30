package com.heystyles.seguridad.api.service;

import com.heystyles.common.service.Service;
import domain.PermisoAuth0;
import domain.PermisoAuth0Extended;

import java.util.List;

public interface PermisoService extends Service<PermisoAuth0, Long> {

    PermisoAuth0 getPermiso(Long permisoId);

    List<PermisoAuth0Extended> getPermisosExtended();

}
