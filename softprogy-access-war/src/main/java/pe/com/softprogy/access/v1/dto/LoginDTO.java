
package pe.com.softprogy.access.v1.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import pe.com.softprogy.security.config.UserWrapper;

@Data
@JsonInclude(value = Include.NON_NULL)
public class LoginDTO
{

    private String sessionId;
    private String email;
    private String name;
    private String phone;
    private Boolean existImage;
    private List<UserRoleDTO> roles;

    public LoginDTO()
    {
    }

    public LoginDTO(UserWrapper source)
    {
        BeanUtils.copyProperties(source, this, "sessionId", "roles");
        this.setRoles(source.getRoles().stream().map( role -> new UserRoleDTO(role)).collect(Collectors.toList()));
    }
}
