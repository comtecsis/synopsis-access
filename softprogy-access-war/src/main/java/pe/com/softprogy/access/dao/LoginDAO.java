
package pe.com.softprogy.access.dao;

import pe.com.softprogy.access.commons.request.LoginRequest;
import pe.com.softprogy.access.dao.domain.AccessResult;
import pe.com.softprogy.security.beans.domain.User;

public interface LoginDAO
{
    public AccessResult<User> login(LoginRequest request);
}
