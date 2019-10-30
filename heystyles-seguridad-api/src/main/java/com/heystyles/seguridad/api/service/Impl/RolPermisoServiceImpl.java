package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.dao.RolPermisoDao;
import com.heystyles.seguridad.api.entity.PermisoEntity;
import com.heystyles.seguridad.api.entity.RolEntity;
import com.heystyles.seguridad.api.entity.RolPermisoEntity;
import com.heystyles.seguridad.api.service.RolPermisoService;
import domain.PermisoAuth0Extended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RolPermisoServiceImpl implements RolPermisoService {

    @Autowired
    private RolPermisoDao rolPermisoDao;

    @Autowired
    private ConverterService converterService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void uppsert(Long rolId, List<Long> permisos) {
        List<RolPermisoEntity> existing = rolPermisoDao.findByRolId(rolId);
        if (existing.size() == 0) {
            return;
        }
        RolEntity rolEntity = existing.get(0).getRol();
        List<RolPermisoEntity> toDelete = new ArrayList<>();
        List<RolPermisoEntity> toSave = new ArrayList<>();

        Set<Long> oldPermisosIds = existing
                .stream()
                .map(e -> e.getPermiso().getId())
                .collect(Collectors.toSet());

        Set<Long> newPermisosIds = new HashSet<>(permisos);

        existing.stream()
                .filter(p -> !newPermisosIds.contains(p.getPermiso().getId()))
                .forEach(p -> toDelete.add(p));

        newPermisosIds.stream()
                .filter(l -> !oldPermisosIds.contains(l))
                .forEach(l -> {
                    RolPermisoEntity entity = new RolPermisoEntity();
                    entity.setRol(rolEntity);
                    entity.setPermiso(new PermisoEntity(l));
                    toSave.add(entity);
                });

        rolPermisoDao.delete(toDelete);
        rolPermisoDao.save(toSave);
    }

    @Override
    public List<PermisoAuth0Extended> findByRolId(Long rolId) {
        return converterService.convertTo(
                rolPermisoDao.findByRolId(rolId).stream().map(RolPermisoEntity::getPermiso).collect(Collectors.toList()),
                PermisoAuth0Extended.class);
    }
}
