package com.juancalderondev.apiuser.repositories;

import com.juancalderondev.apiuser.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByEmail(String emailToCheck);

//    @Query("SELECT a, b, c " +
//            "FROM users a " +
//            "LEFT JOIN roles b" +
//            "LEFT JOIN endpoints c" +
//            "ON a.id = b.")
//    List<Users> getRolesAndOperations();
}
