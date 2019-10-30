package com.heystyles.seguridad.api.service;

import com.heystyles.common.service.Service;
import domain.MenuExtended;

public interface MenuService extends Service<MenuExtended, Long> {

    MenuExtended getMenuByUsuario(Long usuario);
}
