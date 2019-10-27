package com.heystyles.seguridad.cliente;

import dto.RequestRolAuth0;

public interface RolClient {

    String create(RequestRolAuth0 requestRolAuth0);

    void update(String rolId, RequestRolAuth0 requestRolAuth0);

    void delete(String rolId);

}
