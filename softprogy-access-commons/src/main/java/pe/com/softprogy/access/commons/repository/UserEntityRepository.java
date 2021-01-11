
package pe.com.softprogy.access.commons.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.softprogy.access.commons.entity.UserEntity;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long>
{
    
}
