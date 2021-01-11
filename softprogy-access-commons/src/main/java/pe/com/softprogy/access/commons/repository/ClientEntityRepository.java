
package pe.com.softprogy.access.commons.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.softprogy.access.commons.entity.ClientEntity;

@Repository
public interface ClientEntityRepository extends CrudRepository<ClientEntity, Long>
{
    public Optional<ClientEntity> findByEmail(String email);
    public Optional<ClientEntity> findByFkUserAndEmail(Long id, String email);
    public Optional<ClientEntity> findByIdAndEmailNotAndFkUser(Long id, String email, Long fkUser);
}
