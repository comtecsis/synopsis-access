
package pe.com.softprogy.access.commons.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import pe.com.softprogy.access.commons.utils.Constants;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ClientListDTO implements Serializable
{

    private static final long serialVersionUID = Constants.SERIALIZATION_VERSION;

    private Iterable<ClientDTO> clients;

}
