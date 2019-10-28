package com.heystyles.seguridad.cliente;

import dto.RequestRolAuth0;

public interface RolClient {

    Long create(RequestRolAuth0 requestRolAuth0);

    void update(Long rolId, RequestRolAuth0 requestRolAuth0);

    void delete(Long rolId);

}
