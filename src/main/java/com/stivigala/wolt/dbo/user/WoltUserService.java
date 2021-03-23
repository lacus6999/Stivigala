package com.stivigala.wolt.dbo.user;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.Authority;
import com.stivigala.wolt.dbo.authority.AuthorityRepository;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WoltUserService {

    private final WoltUserRepository woltUserRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public WoltUserService(WoltUserRepository woltUserRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.woltUserRepository = woltUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public void register(String userName, String password, Boolean enabled, List<Address> addresses, AuthorityType authority) {
        WoltUser woltUser = new WoltUser(userName, passwordEncoder.encode(password), enabled, addresses);
        woltUserRepository.save(woltUser);
        authorityRepository.save(new Authority(woltUser.getUsername(), authority));
    }

}
