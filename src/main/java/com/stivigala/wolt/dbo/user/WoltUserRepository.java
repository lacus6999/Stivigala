package com.stivigala.wolt.dbo.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WoltUserRepository extends CrudRepository<WoltUser, String> {
    Optional<WoltUser> findByEmail(String email);
}
