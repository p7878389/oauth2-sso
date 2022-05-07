package com.shareworks.auth.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author martin.peng
 */
@RestController
public class UserController {

    @GetMapping("/getUserInfo")
    public Principal user(Principal principal) {
        return principal;
    }
}
