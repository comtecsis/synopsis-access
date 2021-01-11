
package pe.com.softprogy.access.commons.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.softprogy.access.commons.entity.RoleEntity;

@Repository
public interface RoleEntityRepository extends CrudRepository<RoleEntity, Long>
{

}
