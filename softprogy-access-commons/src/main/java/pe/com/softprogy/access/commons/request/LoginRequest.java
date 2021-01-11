
package pe.com.softprogy.access.commons.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class LoginRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String email;
    private String environment;
    private BigDecimal appVersion;
    private String password;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(String environment)
    {
        this.environment = environment;
    }

    public BigDecimal getAppVersion()
    {
        return appVersion;
    }

    public void setAppVersion(BigDecimal appVersion)
    {
        this.appVersion = appVersion;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
