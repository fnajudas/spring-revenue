package Revenue.Using.Springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstname;

    private String lastname;

    private String phone;

    private String email;

    private String password;

    private String username;
}
