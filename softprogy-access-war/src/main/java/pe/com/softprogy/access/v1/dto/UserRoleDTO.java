
package pe.com.softprogy.access.v1.dto;

import lombok.Getter;
import lombok.Setter;
import pe.com.softprogy.security.config.UserRole;

@Getter
@Setter
public class UserRoleDTO
{

    private String authority;

    UserRoleDTO(UserRole userRole)
    {
        this.authority = userRole.getAuthority();
    }
}
