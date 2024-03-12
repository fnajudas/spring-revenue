package Revenue.Using.Springboot.controller;

import Revenue.Using.Springboot.model.GlobalResponse;
import Revenue.Using.Springboot.model.UserRequest;
import Revenue.Using.Springboot.model.UserResponse;
import Revenue.Using.Springboot.repository.users.UserModel;
import Revenue.Using.Springboot.service.UserServices;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    UserServices userServices;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<UserResponse>>> getAllUsers() {
        GlobalResponse<List<UserResponse>> bResp = userServices.getUser();
        if (!bResp.getMessage().equals("Success")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bResp);
        }

        return ResponseEntity.status(HttpStatus.OK).body(bResp);
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GlobalResponse<String>> createUser(@Valid @RequestBody UserRequest bReq) {
        if (bReq.getFirstname() == null || bReq.getFirstname().isEmpty() || bReq.getFirstname().length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GlobalResponse<>("Firstname cant not be empty", null ));
        }

        if (bReq.getPhone() == null || bReq.getPhone().isEmpty() || bReq.getPhone().length() > 13) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GlobalResponse<>("Phone cant not be empty", null ));
        }

        if (bReq.getPassword() == null || bReq.getPassword().isEmpty() || bReq.getPassword().length() > 13) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GlobalResponse<>("Password cant not null and must be at most 13 characters long", null));
        }

        if (bReq.getUsername() == null || bReq.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GlobalResponse<>("Username cant be empty", null ));
        }

        GlobalResponse<String> response = userServices.createUser(bReq);

        if (!response.getMessage().equals("Success")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }
}

