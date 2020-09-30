package com.yosa.rankast.controllers;

import com.auth0.jwt.JWT;
import com.yosa.rankast.domain.Account;
import com.yosa.rankast.domain.Role;
import com.yosa.rankast.dtos.AccountDto;
import com.yosa.rankast.dtos.LoginDto;
import com.yosa.rankast.dtos.TokenDto;
import com.yosa.rankast.dtos.AccountReadDto;
import com.yosa.rankast.exceptions.BadRequestException;
import com.yosa.rankast.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.yosa.rankast.security.SecurityConstants.EXPIRATION_TIME;
import static com.yosa.rankast.security.SecurityConstants.SECRET;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public TokenDto login(@RequestBody LoginDto account){
        Account user = accountService.getByUsername(account.username);

        if(!passwordEncoder.matches(account.password, user.getPassword()))
            throw new BadRequestException("Bad credentials");

        List<String> user_roles = new ArrayList<String>();
        user.getRoles().forEach(e -> user_roles.add(e.name()));
        String roles = String.join(",", user_roles);

        String token = JWT.create()
                .withSubject(account.username).withClaim("Roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        user.setToken(token);

        return modelMapper.map(user, TokenDto.class);
    }

    @PostMapping("user-signup")
    public ResponseEntity<Account> userSignup(@RequestBody AccountReadDto account){
        if(accountService.isAccountExist(account.username))
            throw new BadRequestException("Username is already in use");
        return new ResponseEntity<>(
                accountService.create(new Account(account.username, account.password, account.email, account.fullName), Role.ROLE_USER), HttpStatus.CREATED);
    }

    @PostMapping("owner-signup")
    public ResponseEntity<Account> ownerSignup(@RequestBody AccountReadDto account){
        if(accountService.isAccountExist(account.username))
            throw new BadRequestException("Username is already in use");
        return new ResponseEntity<>(
                accountService.create(new Account(account.username, account.password, account.email, account.fullName), Role.ROLE_OWNER), HttpStatus.CREATED);
    }
}
