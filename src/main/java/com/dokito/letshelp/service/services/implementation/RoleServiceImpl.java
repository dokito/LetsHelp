package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.Role;
import com.dokito.letshelp.data.repositories.RoleRepository;
import com.dokito.letshelp.service.models.RoleServiceModel;
import com.dokito.letshelp.service.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final ModelMapper mapper;

    public RoleServiceImpl(RoleRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void seedRolesInDb() {
        if (this.repository.count() == 0){
            this.repository.saveAndFlush(new Role("ROLE_USER"));
            this.repository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.repository.saveAndFlush(new Role("ROLE_ROOT"));
            this.repository.saveAndFlush(new Role("ROLE_CONTRIBUTOR"));
        }
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.repository.findAll()
                .stream()
                .map(r -> this.mapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.mapper.map(this.repository.findByAndAuthority(authority),RoleServiceModel.class);
    }
}
