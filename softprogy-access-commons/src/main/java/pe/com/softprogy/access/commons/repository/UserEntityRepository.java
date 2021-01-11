
package pe.com.softprogy.access.commons.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.softprogy.access.commons.entity.UserEntity;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long>
{

    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByIdAndEmailNot(Long id, String email);
    
}
