
package pe.com.softprogy.access.v1.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pe.com.softprogy.access.commons.request.LoginRequest;
import pe.com.softprogy.access.commons.request.LogoffRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.commons.response.Status;
import pe.com.softprogy.access.enumeration.AccessCodeEnum;
import pe.com.softprogy.access.enumeration.AccessPropsEnum;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.access.security.SecurityRequest;
import pe.com.softprogy.access.v1.dto.LoginDTO;
import pe.com.softprogy.access.v1.service.LoginService;
import pe.com.softprogy.security.config.JwtTokenProvider;
import pe.com.softprogy.security.config.SecurityToken;

@Service
public class LoginServiceImpl implements LoginService
{

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private Environment env;

    @Override
    public Response<LoginDTO> login(LoginRequest login) throws AccessLogicException
    {
        Response<LoginDTO> response = new Response<LoginDTO>(AccessCodeEnum.OK.status());
        try
        {
            SecurityToken token = (SecurityToken) authenticationManager
                    .authenticate(new SecurityRequest(login.getEmail(), null, login));
            SecurityContextHolder.getContext().setAuthentication(token);
            LoginDTO lr = new LoginDTO(token.getUserInfo());
            String jwt = tokenProvider.generateToken(token);
            lr.setSessionId(String.format("%s %s", AccessPropsEnum.JWT_PREFIX.getString(env), jwt));
            response.setData(lr);
            return response;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        throw new AccessLogicException(AccessCodeEnum.NOT_AUTHENTICATED);
    }

    @Override
    public Status logout(LogoffRequest request)
    {
        return AccessCodeEnum.OK.status();
    }
}
