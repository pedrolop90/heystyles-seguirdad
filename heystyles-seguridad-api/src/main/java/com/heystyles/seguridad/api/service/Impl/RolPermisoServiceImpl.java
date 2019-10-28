package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.dao.RolPermisoDao;
import com.heystyles.seguridad.api.entity.PermisoEntity;
import com.heystyles.seguridad.api.entity.RolEntity;
import com.heystyles.seguridad.api.entity.RolPermisoEntity;
import com.heystyles.seguridad.api.service.RolPermisoService;
import domain.PermisoAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void uppsert(Long rolId, List<PermisoAuth0> permisos) {
        List<RolPermisoEntity> existing = rolPermisoDao.findByRolId(rolId);
        if (existing.size() == 0) {
            return;
        }
        RolEntity rolEntity = existing.get(0).getRol();
        List<RolPermisoEntity> toDelete = new ArrayList<>();
        List<RolPermisoEntity> toSave = new ArrayList<>();

        Set<Long> oldCiudadIds = existing
                .stream()
                .map(e -> e.getPermiso().getId())
                .collect(Collectors.toSet());

        Set<PermisoAuth0> newCiudadIds = new HashSet<>(permisos);

        existing.stream()
                .filter(p -> !newCiudadIds.contains(p.getPermiso().getId()))
                .forEach(p -> toDelete.add(p));

        newCiudadIds.stream()
                .filter(l -> !oldCiudadIds.contains(l))
                .forEach(l -> {
                    RolPermisoEntity entity = new RolPermisoEntity();
                    entity.setRol(rolEntity);
                    entity.setPermiso(new PermisoEntity(l.getId()));
                    toSave.add(entity);
                });

        rolPermisoDao.delete(toDelete);
        rolPermisoDao.save(toSave);
    }

    @Override
    public List<PermisoAuth0> findByRolId(Long rolId) {
        return converterService.convertTo(
                rolPermisoDao.findByRolId(rolId).stream().map(RolPermisoEntity::getPermiso).collect(Collectors.toList()),
                PermisoAuth0.class);
    }
}
