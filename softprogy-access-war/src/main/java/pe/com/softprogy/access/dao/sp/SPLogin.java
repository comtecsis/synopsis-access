
package pe.com.softprogy.access.dao.sp;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

import pe.com.softprogy.access.dao.mapper.LoginMapper;

@Component
public class SPLogin extends SPStoredProcedure
{
    private static final String NAME_PROCEDURE = "SP_LOGIN";

    public static final String SP_IN_EMAIL = "email";
    public static final String SP_IN_PASSWORD = "password";

    public static final String SP_OUT_STATUS = "status";
    public static final String SP_OUT_CURSOR = "cursor";

    public static final String COLUMN_USER_ID = "PK_USER";
    public static final String COLUMN_NAME = "TXT_NAME";
    public static final String COLUMN_EMAIL = "TXT_EMAIL";
    public static final String COLUMN_PHONE = "TXT_PHONE";
    public static final String COLUMN_ROLE = "TXT_ROLE";
    public static final String COLUMN_EXIST_IMAGE = "existImage";

    public SPLogin(JdbcTemplate jdbcTemplate)
    {
        super(jdbcTemplate, NAME_PROCEDURE);
    }

    @Override
    public void configSP()
    {
        getSimpleJdbcCall().useInParameterNames(SP_IN_EMAIL, SP_IN_PASSWORD)
                .declareParameters(new SqlOutParameter(SP_OUT_STATUS, Types.VARCHAR),
                        new SqlParameter(SP_IN_EMAIL, Types.VARCHAR), new SqlParameter(SP_IN_PASSWORD, Types.VARCHAR))
                .returningResultSet(SP_OUT_CURSOR, new LoginMapper());
    }

}
