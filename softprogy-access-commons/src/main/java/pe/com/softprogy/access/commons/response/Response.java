
package pe.com.softprogy.access.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Response<T>
{

    private final Status status;
    private T data;

    public Response(Status status)
    {
        this.status = status;
    }

    public Response(Status status, T data)
    {
        this(status);
        this.data = data;
    }

    public Status getStatus()
    {
        return status;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

}
