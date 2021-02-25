package com.stivigala.wolt.dbo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends CrudRepository<Storage, Integer> {
}
