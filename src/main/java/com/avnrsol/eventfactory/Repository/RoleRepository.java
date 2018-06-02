package com.avnrsol.eventfactory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    Role findById(Long id);
    @Override
    void delete(Role role);

    Role findByUsers_Id(Long userId);
}
