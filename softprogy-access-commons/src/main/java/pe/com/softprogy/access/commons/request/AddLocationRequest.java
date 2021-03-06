
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddLocationRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Transient
    private Long userId;

    private String tag;
    private String address;

}
