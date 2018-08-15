package com.squeezer.oauth2.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;


    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String adminTest(Authentication authentication) {
        System.out.println(authentication.getName());
        return "Hello ADMIN";
    }


    @GetMapping(value = "/user")
    public String userTest() {
        return "Hello User";
    }

    @GetMapping(value = "/user/resetPassword")
    public String password() {
        return "Hello password";
    }

    /**
     * Signout WBS
     * Deletes access token and refresh token from the database
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/signout")
    @ResponseBody
    public void revokeToken(HttpServletRequest request) {
        String accessHeader = request.getHeader("Authorization");
        if (accessHeader != null) {
            String tokenValue = accessHeader.replace("Bearer", "").trim();
            tokenServices.revokeToken(tokenValue);
        }
    }


}