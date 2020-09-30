package com.yosa.rankast.services;

import com.yosa.rankast.domain.Account;
import com.yosa.rankast.domain.Role;
import com.yosa.rankast.exceptions.NotFoundException;
import com.yosa.rankast.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account getByUsername(String username){
        Account user = accountRepository.findByUsername(username);
        if(user == null)
            throw new NotFoundException("User with username = " + username + " not found.");
        return user;
    }

    public Account getById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id = " + id + " not found."));
    }

    public Account create(Account account, Role role){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRoles(Collections.singleton(role));
        return accountRepository.save(account);
    }

    public boolean isAccountExist(String username){
        return accountRepository.findByUsername(username) != null;
    }
}
