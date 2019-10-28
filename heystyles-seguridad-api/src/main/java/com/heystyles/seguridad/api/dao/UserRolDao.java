package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolDao extends JpaRepository<UserRolEntity, Long> {

    UserRolEntity findByUserIdAndRolId(Long userId, Long rolId);

    List<UserRolEntity> findByUserId(Long userId);
}
