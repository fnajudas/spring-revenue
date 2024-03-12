package Revenue.Using.Springboot.service;

import Revenue.Using.Springboot.model.GlobalResponse;
import Revenue.Using.Springboot.model.UserRequest;
import Revenue.Using.Springboot.model.UserResponse;
import Revenue.Using.Springboot.repository.users.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserStore userStore;

     public GlobalResponse<List<UserResponse>> getUser() {
       List<UserResponse> result = userStore.getUser();
       if (result == null) {
           return GlobalResponse.<List<UserResponse>>builder().message("Data not found").data(null).build();
       }

       return GlobalResponse.<List<UserResponse>>builder().message("Success").data(result).build();
    }

    public GlobalResponse<String> createUser(UserRequest bReq) {
        String result = userStore.save(bReq);
        if (!result.equals("Insert Success")) {
            return GlobalResponse.<String>builder().message("Error create user: " + result).data(null).build();
        }
        return GlobalResponse.<String>builder().message("Success").data(result).build();
    }
}
