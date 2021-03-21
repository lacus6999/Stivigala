package com.stivigala.wolt.dbo.user;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.AuthorityService;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WoltUserService {

    private final WoltUserRepository woltUserRepository;

    private final AuthorityService authorityService;

    private final PasswordEncoder passwordEncoder;

    public WoltUserService(AuthorityService authorityService, WoltUserRepository woltUserRepository, PasswordEncoder passwordEncoder) {
        this.authorityService = authorityService;
        this.woltUserRepository = woltUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String userName, String password, Boolean enabled, List<Address> addresses, AuthorityType authority) {
        woltUserRepository.save(new WoltUser(userName, passwordEncoder.encode(password), enabled, addresses));
        authorityService.addAuthority(userName, authority);
    }

}
