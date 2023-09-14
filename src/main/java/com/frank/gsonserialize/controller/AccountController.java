package com.frank.gsonserialize.controller;

import com.frank.gsonserialize.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: maxinhang.
 * @version: 2023/9/14 10:35.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/author/get")
    public Account getAuthorAccount() {
        Account account = new Account();
        account.setName("frank");
        account.setAge(18);
        account.setGender(null);
        account.setArea("China");
        return account;
    }
}
