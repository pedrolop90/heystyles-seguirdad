package com.heystyles.seguridad.api.service;

import domain.PermisoAuth0;

import java.util.List;

public interface RolPermisoService {

    void uppsert(Long rolId, List<PermisoAuth0> permisos);

    List<PermisoAuth0> findByRolId(Long rolId);
}
