
package pe.com.softprogy.access.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import pe.com.softprogy.access.commons.request.LoginRequest;

public class SecurityRequest extends UsernamePasswordAuthenticationToken
{
    private final LoginRequest request;

    private static final long serialVersionUID = -1389274041335460575L;

    public SecurityRequest(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
            LoginRequest request)
    {
        super(principal, credentials, authorities);
        this.request = request;
    }

    public SecurityRequest(Object principal, Object credentials, LoginRequest request)
    {
        super(principal, credentials);
        this.request = request;
    }

    public LoginRequest getRequest()
    {
        return request;
    }

}
