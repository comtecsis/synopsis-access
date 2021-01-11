
package pe.com.softprogy.access.enumeration;

public enum RoleType
{
    // @formatter:off
    CLIENT("ROLE_CLIENT"), 
    ADMIN("ROLE_ADMIN");
    // @formatter:on

    private String code;

    RoleType(String code)
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
