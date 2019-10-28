package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolDao extends JpaRepository<RolEntity, Long> {
}
