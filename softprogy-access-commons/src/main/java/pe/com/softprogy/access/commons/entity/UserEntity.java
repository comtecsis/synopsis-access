
package pe.com.softprogy.access.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import pe.com.softprogy.access.commons.utils.Constants;

@Entity
@Table(name = "USER")
@Data
public class UserEntity implements Serializable
{

    private static final long serialVersionUID = Constants.SERIALIZATION_VERSION;

    @Id
    @Column(name = "PK_USER")
    private Long id;

    @Column(name = "TXT_PASS")
    private String accessKey;

    @OneToOne
    @JoinColumn(name = "PK_USER", referencedColumnName = "PK_ROLE")
    private RoleEntity role;

}
