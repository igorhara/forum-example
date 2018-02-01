package igor.forum.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by igorhara on 31/01/2018.
 */
@PreAuthorize("hasAuthority('USER')")
@RestController
@RequestMapping("/api/user")
public class AuthController {



    @PostMapping(path = "/isLogged")
    public UserDetails getLoggedInUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }

    @GetMapping("/test")
    public String test() {
        return "Should be authenticated";
    }
}
