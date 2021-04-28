package com.stivigala.wolt.service.user;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.address.AddressRepository;
import com.stivigala.wolt.dbo.authority.Authority;
import com.stivigala.wolt.dbo.authority.AuthorityRepository;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.dbo.user.WoltUser;
import com.stivigala.wolt.dbo.user.WoltUserRepository;
import com.stivigala.wolt.service.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class WoltUserService {

    private final WoltUserRepository woltUserRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    private final EmailService emailService;

    public void register(WoltUser woltUser, String address, AuthorityType authority) {
        Address addressObj = new Address(null, address, woltUser);

        woltUser.setAddresses(Collections.singletonList(addressObj));
        woltUser.setPassword(passwordEncoder.encode(woltUser.getPassword()));
        woltUser.setEnabled(false);
        woltUser.setRestaurants(new ArrayList<>());

        woltUserRepository.save(woltUser);
        addressRepository.save(addressObj);
        authorityRepository.save(new Authority(woltUser.getUsername(), authority));
        emailService.sendConfirmationEmail(woltUser);
    }

    public void updateUser(WoltUser woltUser) {
        woltUserRepository.save(woltUser);
    }

    public WoltUser getCurrentAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return woltUserRepository.findById(authentication.getName()).orElse(null);
        }
        else throw new Exception("No authenticated user!");
    }

    public void enableUser(WoltUser woltUser) {
        woltUser.setEnabled(true);
        woltUserRepository.save(woltUser);
    }

    public void confirmUserViaToken(String token) throws Exception {
        WoltUser woltUser = emailService.confirm(token);
        if(woltUser == null)
            throw new IllegalStateException("Error confirming token!");
        else
            enableUser(woltUser);
    }
}
