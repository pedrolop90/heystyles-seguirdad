package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.PermisoEntity;

import java.util.List;

public interface RolPermisoCustomDao {

    List<PermisoEntity> findByUserId(Long userId);

}
