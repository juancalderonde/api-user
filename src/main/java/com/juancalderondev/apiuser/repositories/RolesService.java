package com.juancalderondev.apiuser.repositories;

import com.juancalderondev.apiuser.models.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolesService extends CrudRepository<Roles, Long> {
    Optional<Roles> findById(long id);

    @Query(value="SELECT b.endpoint_uri, c.name as operation_name " +
            "FROM `roles` a " +
            "LEFT JOIN `endpoints` b " +
            "ON a.endpoint_id = b.id " +
            "LEFT JOIN `operations` c " +
            "ON a.operation_id = c.id " +
            "WHERE a.id = ?1", nativeQuery = true)
    Optional<Object[][]> findCustomById(Long id);
}
