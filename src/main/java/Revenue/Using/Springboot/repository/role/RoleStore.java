package Revenue.Using.Springboot.repository.role;

import Revenue.Using.Springboot.model.RoleRequest;
import Revenue.Using.Springboot.repository.usergroup.UserGroupModel;
import Revenue.Using.Springboot.repository.usergroup.UserGroupRepository;
import Revenue.Using.Springboot.utilities.Time;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.sql.Timestamp;

@Service
public class RoleStore {
    @Autowired
    RoleRepository roleRepository;

    Timestamp timeNow = Time.getTimeNow();

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Transactional
    public String createRole(RoleRequest bReq) {
        if (roleRepository.findByName(bReq.getName()).isPresent()) {
            return "Role already exists";
        }

        UserGroupModel userGroupModel = new UserGroupModel();
        userGroupModel.setName(bReq.getName());
        userGroupModel.setCreatedAt(timeNow);
        userGroupRepository.save(userGroupModel);

        return "Role created";
    }
}
