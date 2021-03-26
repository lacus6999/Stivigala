package com.stivigala.wolt.dbo.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    List<Authority> findAllByAuthority(AuthorityType authority);
}
