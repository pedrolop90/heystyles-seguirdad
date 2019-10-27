package com.heystyles.seguridad.cliente;

import domain.EstadoUser;
import domain.UserAuth0;
import domain.UsuarioAuth0;

public interface UserCliente {

    String createUser(UserAuth0 userAuth0);

    void updateUser(String idUsuario, UsuarioAuth0 usuario);

    void assignRolToUser(String userId, String rolId);

    void updateRolUser(String userId, String rolLastId, String rolNewId);

    EstadoUser removeRolToUser(String userId, String rolId);
}
