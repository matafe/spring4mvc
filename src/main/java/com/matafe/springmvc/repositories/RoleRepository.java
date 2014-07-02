package com.matafe.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matafe.springmvc.entities.Role;

/**
 * @author Mauricio T. Ferraz
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
