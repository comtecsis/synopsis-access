
package pe.com.softprogy.access.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TXT_NAME")
    private String name;

    @Column(name = "TXT_EMAIL")
    private String email;

    @Column(name = "TXT_PHONE")
    private String phone;

    @Column(name = "FILE_IMG")
    private String fileImg;
    
    @Column(name = "FK_USER")
    private Long fkUser;

    @OneToOne
    @JoinColumn(name = "PK_CLIENT", referencedColumnName = "PK_USER")
    private UserEntity user;
}
