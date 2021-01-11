
package pe.com.softprogy.access.enumeration;

public enum LoginType
{
    // @formatter:off
    GOOGLE("001"), 
    EMAIL("002");
    // @formatter:on

    private String code;

    LoginType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public boolean isCode(String code)
    {
        return this.code.equals(code);
    }
}
