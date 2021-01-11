
package pe.com.softprogy.access.exception;

import pe.com.softprogy.access.enumeration.AccessCodeEnum;

public class AccessLogicException extends Exception
{
    private static final long serialVersionUID = -8905975451003033528L;

    private final AccessCodeEnum code;

    public AccessLogicException(AccessCodeEnum arg0, Throwable arg1)
    {
        super(arg0.getMessage(), arg1);
        code = arg0;
    }

    public AccessLogicException(AccessCodeEnum arg0)
    {
        super(arg0.getMessage());
        code = arg0;
    }

    public AccessCodeEnum getCode()
    {
        return code;
    }

}
