package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.seguridad.api.async.EnviarPasswordInicialUsuarioData;
import com.heystyles.seguridad.api.async.EnviarPasswordInicialUsuarioTask;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.entity.UserEntity;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.service.UserService;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserAuth0, UserEntity, Long> implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("threadPoolExecutor")
    private SchedulingTaskExecutor executor;

    @Override
    public Long createUserAuth0(UserAuth0 user) {
        Long userId = super.insert(user);
        roleService.assignRoleToUser(user.getRoleIdSecurity(), userId);
        enviarEmail(user.getEmail(), user.getPassword());
        return userId;
    }

    private void enviarEmail(String email, String password) {
        EnviarPasswordInicialUsuarioData data = new EnviarPasswordInicialUsuarioData();
        data.setEmail(email);
        data.setPassword(password);
        EnviarPasswordInicialUsuarioTask task = context.getBean(EnviarPasswordInicialUsuarioTask.class);
        task.setData(data);
        executor.submit(task);
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
