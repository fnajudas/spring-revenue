package Revenue.Using.Springboot.service;

import Revenue.Using.Springboot.model.GlobalResponse;
import Revenue.Using.Springboot.model.UserGroupRequest;
import Revenue.Using.Springboot.repository.usergroup.UserGroupStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServices {
    @Autowired
    private UserGroupStore userGroupStore;

    public GlobalResponse<String> CreateUserGroup(UserGroupRequest bReq) {
        String result = userGroupStore.save(bReq);
        if (!result.equals("Insert Success")){
            return GlobalResponse.<String>builder().message("Error create user group: " + result).data(null).build();
        }
        return GlobalResponse.<String>builder().message("Success").data(result).build();
    }
}
