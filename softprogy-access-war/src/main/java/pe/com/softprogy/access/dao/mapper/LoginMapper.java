
package pe.com.softprogy.access.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.softprogy.access.dao.sp.SPLogin;
import pe.com.softprogy.security.beans.domain.User;

public class LoginMapper implements RowMapper<User>
{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        User user = new User();
        user.setUserId(rs.getLong(SPLogin.COLUMN_USER_ID));
        user.setName(rs.getString(SPLogin.COLUMN_NAME));
        user.setEmail(rs.getString(SPLogin.COLUMN_EMAIL));
        user.setPhone(rs.getString(SPLogin.COLUMN_PHONE));
        user.setRole(rs.getString(SPLogin.COLUMN_ROLE));
        user.setExistImage(rs.getBoolean(SPLogin.COLUMN_EXIST_IMAGE));
        return user;
    }

}
