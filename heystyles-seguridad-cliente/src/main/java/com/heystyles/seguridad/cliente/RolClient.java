package com.heystyles.seguridad.cliente;

import domain.PermisoAuth0Extended;
import domain.RolAuth0;

import java.util.List;

public interface RolClient {

    Long create(RolAuth0 rolAuth0);

    void update(Long rolId, RolAuth0 rolAuth0);

    void delete(Long rolId);

    List<PermisoAuth0Extended> getPermisos(Long rolId);

}
