package com.stivigala.wolt.dbo.authority;

import com.stivigala.wolt.dbo.user.WoltUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, WoltUser> {
}
