
package pe.com.softprogy.access.v1.service;

import pe.com.softprogy.access.commons.dto.ClientDTO;
import pe.com.softprogy.access.commons.dto.ClientListDTO;
import pe.com.softprogy.access.commons.request.AddClientRequest;
import pe.com.softprogy.access.commons.request.DeleteClientRequest;
import pe.com.softprogy.access.commons.request.EditClientRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.security.config.UserInfo;

public interface ClientService
{

    public Response<ClientDTO> add(UserInfo userInfo, AddClientRequest login) throws AccessLogicException;
    
    public Response<ClientDTO> edit(UserInfo userInfo, EditClientRequest login) throws AccessLogicException;

    public Response<ClientListDTO> list() throws AccessLogicException;

    public void delete(UserInfo userInfo, DeleteClientRequest login) throws AccessLogicException;

}
