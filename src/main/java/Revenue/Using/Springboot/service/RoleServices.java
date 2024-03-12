package Revenue.Using.Springboot.service;

import Revenue.Using.Springboot.model.GlobalResponse;
import Revenue.Using.Springboot.model.RoleRequest;
import Revenue.Using.Springboot.repository.role.RoleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServices {
    @Autowired
    private RoleStore roleStore;

    public GlobalResponse<String> CreateRole(RoleRequest bReq) {
        String result = roleStore.createRole(bReq);
        if (!result.equals("Role created")) {
            return GlobalResponse.<String>builder().message("Error create role: " + result).data(null).build();
        }

        return GlobalResponse.<String>builder().message("Success").data(result).build();
    }
}
