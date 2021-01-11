
package pe.com.softprogy.access.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import pe.com.softprogy.access.commons.utils.Constants;

@Entity
@Table(name = "ROLE")
@Data
public class RoleEntity implements Serializable
{

    private static final long serialVersionUID = Constants.SERIALIZATION_VERSION;

    @Id
    @Column(name = "PK_ROLE")
    private Long id;

    @Column(name = "TXT_ROLE")
    private String name;

}
