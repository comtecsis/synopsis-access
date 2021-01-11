
package pe.com.softprogy.access.v1.service;

import pe.com.softprogy.access.commons.dto.ClientDTO;
import pe.com.softprogy.access.commons.dto.ClientListDTO;
import pe.com.softprogy.access.commons.request.AddClientRequest;
import pe.com.softprogy.access.commons.request.DeleteClientRequest;
import pe.com.softprogy.access.commons.request.EditClientRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.exception.AccessLogicException;

public interface ClientService
{

    public Response<ClientDTO> add(AddClientRequest login) throws AccessLogicException;
    
    public Response<ClientDTO> edit(EditClientRequest login) throws AccessLogicException;

    public Response<ClientListDTO> list() throws AccessLogicException;

    public void delete(DeleteClientRequest login) throws AccessLogicException;

}
