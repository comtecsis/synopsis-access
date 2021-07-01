
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditClientRequest implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    @NotNull
    private String name;
    
    private String loginType;

}
