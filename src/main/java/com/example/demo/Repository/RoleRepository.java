package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    Role findById(Long id);
    @Override
    void delete(Role role);

    Role findByUsers_Id(Long userId);
}
