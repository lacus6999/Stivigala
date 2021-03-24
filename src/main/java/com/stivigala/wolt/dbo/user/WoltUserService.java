package com.stivigala.wolt.dbo.user;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.Authority;
import com.stivigala.wolt.dbo.authority.AuthorityRepository;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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

    public void register(String username, String password, String fullName, Boolean enabled, String phone, List<Address> addresses, AuthorityType authority) {
        WoltUser woltUser = new WoltUser(username, passwordEncoder.encode(password),fullName, enabled, phone,new ArrayList<>(), addresses);
        woltUserRepository.save(woltUser);
        authorityRepository.save(new Authority(woltUser.getUsername(), authority));
    }

    public WoltUser getCurrentAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return woltUserRepository.findById(authentication.getName()).orElseThrow();
        }
        else throw new Exception("No authenticated user!");
    }

}
