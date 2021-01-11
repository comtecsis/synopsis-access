
package pe.com.softprogy.access.enumeration;

/**
 * 
 * @author Elvis <br/>
 *         <b>Se deben usar las siguientes categorias para agrupar los errores.</b>
 *         <ul>
 *         <li>LOGIC: 0001 - 1000</li>
 *         <li>STORE_PROCEDURE: 1001 - 2000</li>
 *         <li>EXTERNAL_SERVICE: 2001 - 3000</li>
 *         </ul>
 */
public enum AccessContext
{
    // @formatter:off
    LOGIC(0), 
    STORE_PROCEDURE(1000), 
    EXTERNAL_SERVICE(2000);
    // @formatter:on

    private Integer base;

    AccessContext(Integer base)
    {
        this.base = base;
    }

    public String getCode(Integer index)
    {
        return String.format("%04d", this.base + index);
    }
}
