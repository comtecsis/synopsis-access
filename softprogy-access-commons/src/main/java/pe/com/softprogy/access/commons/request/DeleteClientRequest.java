
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteClientRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String email;

}
