package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoDao
        extends JpaRepository<PermisoEntity, Long> {
}
