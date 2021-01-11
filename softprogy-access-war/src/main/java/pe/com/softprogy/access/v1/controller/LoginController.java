
package pe.com.softprogy.access.v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.softprogy.access.commons.request.LoginRequest;
import pe.com.softprogy.access.commons.request.LogoffRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.commons.response.Status;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.access.v1.dto.LoginDTO;
import pe.com.softprogy.access.v1.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1")
public class LoginController
{

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<Response<LoginDTO>> login(@Valid @RequestBody LoginRequest login, HttpServletRequest request)
            throws AccessLogicException
    {
        Response<LoginDTO> response = loginService.login(login);
        return new ResponseEntity<Response<LoginDTO>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Status> logout(@Valid @RequestBody LogoffRequest logoffReq, HttpServletRequest request)
    {
        return new ResponseEntity<Status>(loginService.logout(logoffReq), HttpStatus.OK);
    }

}