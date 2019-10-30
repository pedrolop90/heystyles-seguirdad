package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findByEmail(String email);
    UserEntity findByUsuario(String usuario);

}
