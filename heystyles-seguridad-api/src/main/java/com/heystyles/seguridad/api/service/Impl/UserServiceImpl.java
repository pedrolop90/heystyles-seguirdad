package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.entity.UserEntity;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.service.UserService;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserAuth0, UserEntity, Long> implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Override
    public Long createUserAuth0(UserAuth0 user) {
        Long userId = super.insert(user);
        roleService.assignRoleToUser(user.getRoleIdSecurity(), userId);
        return userId;
    }


    @Override
    public void updateUser(Long idUser, UserAuth0 user) {
        super.update(idUser, user);
    }

    @Override
    public void deleteAuth0User(Long idUser) {
        super.delete(idUser);
    }

    @Override
    protected CrudRepository<UserEntity, Long> getDao() {
        return userDao;
    }
}
