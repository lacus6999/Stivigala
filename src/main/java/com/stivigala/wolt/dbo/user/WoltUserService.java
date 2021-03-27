package com.stivigala.wolt.dbo.user;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.Authority;
import com.stivigala.wolt.dbo.authority.AuthorityRepository;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WoltUserService {

    private final WoltUserRepository woltUserRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public void register(String username, String password, String email, String fullName, String phone, List<Address> addresses, AuthorityType authority) {
        WoltUser woltUser = new WoltUser(username, passwordEncoder.encode(password), email, fullName, false, phone,new ArrayList<>(), addresses);
        woltUserRepository.save(woltUser);
        authorityRepository.save(new Authority(woltUser.getUsername(), authority));
        emailService.sendConfirmationEmail(woltUser);
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
