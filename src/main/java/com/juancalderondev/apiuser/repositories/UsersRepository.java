package com.juancalderondev.apiuser.repositories;

import com.juancalderondev.apiuser.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
}
