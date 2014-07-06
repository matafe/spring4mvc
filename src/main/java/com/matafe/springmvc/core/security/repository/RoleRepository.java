package com.matafe.springmvc.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matafe.springmvc.core.security.Role;

/**
 * <p>
 * Role Repository.
 * 
 * @author Mauricio T. Ferraz
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
