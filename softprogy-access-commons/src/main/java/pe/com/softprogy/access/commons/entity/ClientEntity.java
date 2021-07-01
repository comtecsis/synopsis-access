
package pe.com.softprogy.access.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import pe.com.softprogy.access.commons.utils.Constants;

@Entity
@Table(name = "CLIENT")
@Data
public class ClientEntity implements Serializable
{

    private static final long serialVersionUID = Constants.SERIALIZATION_VERSION;

    @Id
    @Column(name = "PK_CLIENT")
    private Long id;

    @Column(name = "TXT_NAME")
    private String name;

    @Column(name = "FILE_IMG")
    private String fileImg;
    
    @Column(name = "FK_USER")
    private Long fkUser;
    
}
