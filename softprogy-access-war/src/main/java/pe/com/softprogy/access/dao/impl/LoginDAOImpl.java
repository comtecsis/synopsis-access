
package pe.com.softprogy.access.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import pe.com.softprogy.access.commons.request.LoginRequest;
import pe.com.softprogy.access.dao.LoginDAO;
import pe.com.softprogy.access.dao.domain.AccessResult;
import pe.com.softprogy.access.dao.sp.SPLogin;
import pe.com.softprogy.security.beans.domain.User;
import pe.com.softprogy.security.config.JwtAuthenticationException;

@Component
public class LoginDAOImpl implements LoginDAO
{
    @Autowired
    private SPLogin spLogin;

    @SuppressWarnings("unchecked")
    @Override
    public AccessResult<User> login(LoginRequest request)
    {
        AccessResult<User> statusCode = new AccessResult<>();

        SqlParameterSource in = new MapSqlParameterSource().addValue(SPLogin.SP_IN_EMAIL, request.getEmail())
                .addValue(SPLogin.SP_IN_PASSWORD, request.getPassword());

        Map<String, Object> out = spLogin.execute(in);
        statusCode.setCode((String) out.get(SPLogin.SP_OUT_STATUS));
        List<User> users = (List<User>) out.get(SPLogin.SP_OUT_CURSOR);

        if (!CollectionUtils.isEmpty(users))
        {
            statusCode.setResult(users.get(0));
        }
        else
        {
            throw new JwtAuthenticationException("No se identifico correctamente");
        }

        return statusCode;
    }

}
