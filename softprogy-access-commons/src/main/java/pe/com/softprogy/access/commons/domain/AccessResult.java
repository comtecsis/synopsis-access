
package pe.com.softprogy.access.commons.domain;

public class AccessResult<T>
{
    private String code;
    private T result;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

}
