package Revenue.Using.Springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String username;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("modified_at")
    private Timestamp modifiedAt;

    private Boolean deleted;
}
