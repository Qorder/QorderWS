package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.repository.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class MenuService implements IMenuService {

    private final IMenuRepository menuRepository;

    private final IMapper<?,?> mapper;

    @Autowired
    public MenuService(IMenuRepository menuRepository, IMapper mapper) {
        this.menuRepository = menuRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public MenuDTO fetchMenuById(long menuId) {
        Menu menu = menuRepository.findOne(menuId);
        return Objects.nonNull(menu)
                ? mapper.map(menu)
                        .to(new MenuDTO())
                        .get()
                : new MenuDTO();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public long addCategory(long menuID, @NotNull CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO)
                .to(new Category())
                .get();

        Menu menu = menuRepository.findOne(menuID);
        menu.addCategory(category);
        menu = menuRepository.save(menu);
        return menu.getCategoryList().stream()
                .reduce((previous, current) -> current)
                .get()
                .getId();
    }

}
