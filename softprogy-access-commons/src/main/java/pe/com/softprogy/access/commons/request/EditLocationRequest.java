
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditLocationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long locationId;

    private String tag;
    private String address;
}
