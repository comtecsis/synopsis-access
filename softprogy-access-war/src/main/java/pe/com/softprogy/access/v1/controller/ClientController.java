
package pe.com.softprogy.access.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.softprogy.access.commons.dto.ClientDTO;
import pe.com.softprogy.access.commons.dto.ClientListDTO;
import pe.com.softprogy.access.commons.request.AddClientRequest;
import pe.com.softprogy.access.commons.request.DeleteClientRequest;
import pe.com.softprogy.access.commons.request.EditClientRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.enumeration.AccessCodeEnum;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.access.v1.service.ClientService;
import pe.com.softprogy.security.config.SecurityToken;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/client")
public class ClientController
{

    @Autowired
    private ClientService service;

    @PostMapping(value = "")
    public ResponseEntity<Response<ClientDTO>> add(@AuthenticationPrincipal SecurityToken token,
            @Valid @RequestBody AddClientRequest client) throws AccessLogicException
    {
        Response<ClientDTO> response = service.add(token.getUserInfo(), client);
        return new ResponseEntity<Response<ClientDTO>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<Response<ClientDTO>> edit(@AuthenticationPrincipal SecurityToken token,
            @Valid @RequestBody EditClientRequest client) throws AccessLogicException
    {
        Response<ClientDTO> response = service.edit(token.getUserInfo(), client);
        return new ResponseEntity<Response<ClientDTO>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<Response<ClientListDTO>> list() throws AccessLogicException
    {
        Response<ClientListDTO> response = service.list();
        return new ResponseEntity<Response<ClientListDTO>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Response<String>> delete(@AuthenticationPrincipal SecurityToken token,
            @Valid @RequestBody DeleteClientRequest client) throws AccessLogicException
    {
        service.delete(token.getUserInfo(), client);
        return new ResponseEntity<Response<String>>(AccessCodeEnum.OK.createResponse(), HttpStatus.OK);
    }

}