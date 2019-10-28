package com.heystyles.seguridad.cliente;

import domain.EstadoUser;
import domain.UserAuth0;

public interface UserCliente {

    Long createUser(UserAuth0 userAuth0);

    void updateUser(Long idUsuario, UserAuth0 usuario);

    void assignRolToUser(Long userId, Long rolId);

    void updateRolUser(Long userId, Long rolLastId, Long rolNewId);

    EstadoUser removeRolToUser(Long userId, Long rolId);
}
