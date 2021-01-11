
package pe.com.softprogy.access.commons.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import pe.com.softprogy.access.commons.entity.UserEntity;
import pe.com.softprogy.access.commons.utils.Constants;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ClientDTO implements Serializable
{

    private static final long serialVersionUID = Constants.SERIALIZATION_VERSION;

    public ClientDTO()
    {
    }

    public ClientDTO(UserEntity user)
    {
        this.setId(user.getId());
        this.setEmail(user.getEmail());
        this.setName(user.getClient().getName());
        this.setPhone(user.getClient().getPhone());
        this.setFileImg(user.getClient().getFileImg());
    }

    private Long id;
    private String email;
    private String phone;
    private String name;
    private String fileImg;

}
