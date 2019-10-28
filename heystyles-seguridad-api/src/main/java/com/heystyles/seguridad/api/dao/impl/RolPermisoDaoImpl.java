package com.heystyles.seguridad.api.dao.impl;

import com.heystyles.seguridad.api.dao.RolPermisoCustomDao;
import com.heystyles.seguridad.api.entity.PermisoEntity;
import com.heystyles.seguridad.api.entity.UserRolEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class RolPermisoDaoImpl implements RolPermisoCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PermisoEntity> findByUserId(Long userId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(UserRolEntity.class);
        criteria.createAlias(UserRolEntity.Attributes.USER, UserRolEntity.Attributes.USER);
        criteria.createAlias(UserRolEntity.Attributes.ROL, UserRolEntity.Attributes.ROL);
        criteria.createAlias(UserRolEntity.Attributes.ROL_ROL_PERMISOS, UserRolEntity.Attributes.ROL_ROL_PERMISOS_ALIAS);
        criteria.createAlias(UserRolEntity.Attributes.ROL_ROL_PERMISOS_PERMISO, UserRolEntity.Attributes.ROL_ROL_PERMISOS_PERMISO_ALIAS);

        criteria.add(Restrictions.eq(UserRolEntity.Attributes.USER_ID, userId));
        criteria.setProjection(Projections.distinct(Projections.property(UserRolEntity.Attributes.ROL_ROL_PERMISOS_PERMISO_ALIAS)));
        return criteria.list();
    }
}
