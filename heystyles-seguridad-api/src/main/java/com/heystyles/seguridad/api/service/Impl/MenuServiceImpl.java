package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.seguridad.api.dao.MenuDao;
import com.heystyles.seguridad.api.dao.RolPermisoDao;
import com.heystyles.seguridad.api.entity.MenuEntity;
import com.heystyles.seguridad.api.entity.PermisoEntity;
import com.heystyles.seguridad.api.service.MenuService;
import domain.Estado;
import domain.MenuExtended;
import domain.PermisoAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl
        extends ServiceImpl<MenuExtended, MenuEntity, Long> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RolPermisoDao rolPermisoDao;

    @Override
    protected CrudRepository<MenuEntity, Long> getDao() {
        return menuDao;
    }

    @Override
    public MenuExtended getMenuByUsuario(Long usuario) {
        MenuExtended menuExtendedPadre = getConverterService().convertTo(menuDao.findTopByMenuPadre(null), MenuExtended.class);
        Map<Long, List<PermisoEntity>> mapResponse = rolPermisoDao.findByUserId(usuario)
                .stream()
                .collect(Collectors.groupingBy(permisoEntity -> permisoEntity.getMenu().getId()));
        menuExtendedPadre.setHijos(construirMenu(mapResponse, menuExtendedPadre.getHijos()));
        return menuExtendedPadre;
    }

    private List<MenuExtended> construirMenu(Map<Long, List<PermisoEntity>> mapData, List<MenuExtended> menuExtendeds) {
        List<MenuExtended> listResponse = new ArrayList<>();
        if (menuExtendeds != null && menuExtendeds.size() > 0) {
            menuExtendeds.forEach(menu -> {
                if (menu.getEstado().compareTo(Estado.ACTIVO) == 0) {
                    if (mapData.containsKey(menu.getId())) {
                        List<PermisoAuth0> permisos = getConverterService().convertTo(mapData.get(menu.getId()), PermisoAuth0.class);
                        menu.setHijos(construirMenu(mapData, menu.getHijos()));
                        menu.setPermisos(permisos);
                        listResponse.add(menu);
                    }
                }
            });
        }
        return listResponse;
    }

}
