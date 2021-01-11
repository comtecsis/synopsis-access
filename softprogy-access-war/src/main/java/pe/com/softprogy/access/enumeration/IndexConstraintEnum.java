
package pe.com.softprogy.access.enumeration;

import org.hibernate.exception.ConstraintViolationException;

/**
 * 
 * @author Elvis
 * 
 */
public enum IndexConstraintEnum
{
    // @formatter:off
    TXT_EMAIL_UNIQUE("TXT_EMAIL_UNIQUE"),
    TXT_PHONE_UNIQUE("TXT_PHONE_UNIQUE"),;
    // @formatter:on

    private final String base;

    IndexConstraintEnum(String base)
    {
        this.base = base;
    }

    public static AccessCodeEnum getCode(String index)
    {
        if (IndexConstraintEnum.TXT_EMAIL_UNIQUE.base.equalsIgnoreCase(index))
        {
            return AccessCodeEnum.EXIST_EMAIL;
        }
        else if (IndexConstraintEnum.TXT_PHONE_UNIQUE.base.equalsIgnoreCase(index))
        {
            return AccessCodeEnum.EXIST_PHONE;
        }
        return AccessCodeEnum.EXIST_FIELD;
    }

    public static AccessCodeEnum getCode(ConstraintViolationException ex)
    {
        return getCode(ex.getConstraintName());
    }

}
