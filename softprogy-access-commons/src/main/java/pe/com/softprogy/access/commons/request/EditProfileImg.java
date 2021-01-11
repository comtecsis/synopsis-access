
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileImg implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long userId;

    private byte[] imgProfile;

}
