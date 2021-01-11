
package pe.com.softprogy.access.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import pe.com.softprogy.access.commons.request.LoginRequest;
import pe.com.softprogy.access.dao.LoginDAO;
import pe.com.softprogy.access.dao.domain.AccessResult;
import pe.com.softprogy.access.enumeration.AccessCodeEnum;
import pe.com.softprogy.security.beans.domain.User;
import pe.com.softprogy.security.config.JwtAuthenticationException;
import pe.com.softprogy.security.config.SecurityToken;
import pe.com.softprogy.security.config.UserRole;
import pe.com.softprogy.security.config.UserWrapper;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider
{

    @Autowired
    private LoginDAO loginDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {

        SecurityRequest request = (SecurityRequest) authentication;
        LoginRequest login = request.getRequest();
        String username = login.getEmail();
        AccessResult<User> status = loginDAO.login(login);

        if (AccessCodeEnum.OK.isCode(status.getCode()))
        {
            User user = status.getResult();
            UserWrapper userInfo = new UserWrapper(user);
            List<UserRole> roles = Arrays.asList(new UserRole(user.getRole()));
            return new SecurityToken(username, null, roles, userInfo);
        }
        else
        {
            throw new JwtAuthenticationException("No se identifico correctamente");
        }
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
