package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.RolPermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolPermisoDao
        extends JpaRepository<RolPermisoEntity, Long>, RolPermisoCustomDao {

    List<RolPermisoEntity> findByRolId(Long rolId);

}
