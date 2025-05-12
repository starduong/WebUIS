package vn.edu.ptithcm.WebUIS.util;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.service.AccountService;

@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService{
    private final AccountService accountService;

    public UserDetailsCustom(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
            account.getUsername(),
            account.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getName())));
    }
    
}
