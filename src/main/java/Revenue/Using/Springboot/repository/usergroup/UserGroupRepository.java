package Revenue.Using.Springboot.repository.usergroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroupModel, Integer> {
    Optional<UserGroupModel> findById(Integer id);
    Optional<UserGroupModel> findByName(String name);
}
