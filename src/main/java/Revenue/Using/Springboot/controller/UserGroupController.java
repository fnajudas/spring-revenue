package Revenue.Using.Springboot.controller;

import Revenue.Using.Springboot.model.GlobalResponse;
import Revenue.Using.Springboot.model.UserGroupRequest;
import Revenue.Using.Springboot.service.UserGroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user-group")
public class UserGroupController {
    @Autowired
    UserGroupServices userGroupServices;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GlobalResponse<String>> createUserGroup(@RequestBody UserGroupRequest bReq) {
        if (bReq.getName() == null || bReq.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(new GlobalResponse<>("Name cannot be empty", null));
        }

        GlobalResponse<String> bResp = userGroupServices.CreateUserGroup(bReq);
        if (!bResp.getMessage().equals("Success")){
            return ResponseEntity.internalServerError().body(bResp);
        }

        return ResponseEntity.ok().body(bResp);
    }
}
