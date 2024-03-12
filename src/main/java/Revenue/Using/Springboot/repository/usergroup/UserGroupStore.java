package Revenue.Using.Springboot.repository.usergroup;

import Revenue.Using.Springboot.model.UserGroupRequest;
import Revenue.Using.Springboot.utilities.Time;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserGroupStore {
    @Autowired
    UserGroupRepository userGroupRepository;

    Timestamp TimeNow = Time.getTimeNow();

    @Transactional
    public String save(UserGroupRequest bReq){
        if (userGroupRepository.findByName(bReq.getName()).isPresent()){
            return "User Group already exists";
        }

        UserGroupModel userGroupModel = new UserGroupModel();
        userGroupModel.setName(bReq.getName());
        userGroupModel.setCreatedAt(TimeNow);

        userGroupRepository.save(userGroupModel);

        return "Insert Success";
    }


}
