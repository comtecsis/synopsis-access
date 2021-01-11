
package pe.com.softprogy.access.enumeration;

import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.commons.response.Status;

/**
 * 
 * @author Elvis <br/>
 *         Se utiliza para manejo y agrupamiento de excepciones. <br/>
 *         Revisar la documentaci&oacute;n de: {@link AccessContext}
 */
public enum AccessCodeEnum
{
    // @formatter:off
    OK(AccessContext.LOGIC, 0, "Consulta exitosa."), 
    NOT_AUTHENTICATED(AccessContext.LOGIC, 1, "No se encuentra autenticado."), 
    MIMETYPE_INVALID(AccessContext.LOGIC, 2, "El tipo de dato es inválido."), 
    STORE_PROCEDURE(AccessContext.LOGIC, 3, "Error inesperado."), 
    FAIL(AccessContext.LOGIC, 1000, "Error inesperado."),

    RESOURCE_NOT_EXIST(AccessContext.STORE_PROCEDURE, 1, "Recurso no existe.");
    // @formatter:on

    private AccessContext context;
    private Status status;

    AccessCodeEnum(AccessContext context, Integer index, String message)
    {
        this.status = new Status(context.getCode(index), message);
        this.context = context;
    }

    public AccessContext getContext()
    {
        return context;
    }

    public String getCode()
    {
        return this.status.getCode();
    }

    public String getMessage()
    {
        return this.status.getMessage();
    }

    public boolean isCode(String code)
    {
        return this.getCode().equals(code);
    }

    public Status status()
    {
        return this.status;
    }
    
    public <T> Response<T> createResponse(T data) 
    {
        return new Response<>(new Status(this.getCode(), this.getMessage()), data);
    }
    
    public <T> Response<T> createResponse() 
    {
        return new Response<>(new Status(this.getCode(), this.getMessage()));
    }

}
