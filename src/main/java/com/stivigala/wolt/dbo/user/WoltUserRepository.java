package com.stivigala.wolt.dbo.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoltUserRepository extends CrudRepository<WoltUser, String> {
}
