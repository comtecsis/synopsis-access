
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddClientRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String phone;
    private String password;
    private String loginType;

}
