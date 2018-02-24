package igor.forum.controller.rest;

import igor.forum.model.UserForum;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * Created by igorhara on 31/01/2018.
 */

@RestController
@RequestMapping("/api/user")
public class AuthController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping(path="/login")
    public UserDetails login(@RequestBody  UserForum userToLogin, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication;
        try {
            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userToLogin.getUsername(),
                    userToLogin.getPassword());
            token.setDetails(new WebAuthenticationDetails(request));
            authentication = this.authenticationManager.authenticate(token);
            logger.debug("Logging in with [{}]", authentication.getPrincipal());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            /*HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);*/
        } catch (AuthenticationException e){
            SecurityContextHolder.getContext().setAuthentication(null);
            logger.error("Failure in autoLogin", e);
            throw e;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }

    @GetMapping(path = "/login")
    public UserDetails isLoggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication).map(Authentication::getPrincipal).filter(p-> p instanceof UserDetails)
                .map(p-> (UserDetails)p).orElse(null);
    }

    @GetMapping(path = "/logout")
    public void logout(HttpSession session){
        SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
    }
}
