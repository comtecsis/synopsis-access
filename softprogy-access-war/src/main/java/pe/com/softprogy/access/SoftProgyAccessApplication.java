
package pe.com.softprogy.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages = { "pe.com.softprogy.access.commons.entity" })
@ComponentScan(basePackages = { "pe.com.softprogy.access", "pe.com.softprogy.security" })
public class SoftProgyAccessApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SoftProgyAccessApplication.class, args);
    }

}
