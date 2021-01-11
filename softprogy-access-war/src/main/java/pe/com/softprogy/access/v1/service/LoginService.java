
package pe.com.softprogy.access.v1.service;

import pe.com.softprogy.access.commons.request.LoginRequest;
import pe.com.softprogy.access.commons.request.LogoffRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.commons.response.Status;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.access.v1.dto.LoginDTO;

public interface LoginService
{

    public Response<LoginDTO> login(LoginRequest login) throws AccessLogicException;

    public Status logout(LogoffRequest request);
}
