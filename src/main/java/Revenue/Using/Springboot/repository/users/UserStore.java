package Revenue.Using.Springboot.repository.users;

import Revenue.Using.Springboot.model.UserRequest;
import Revenue.Using.Springboot.model.UserResponse;
import Revenue.Using.Springboot.utilities.BCrypt;
import Revenue.Using.Springboot.utilities.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStore {
    @Autowired
    UserRepository userRepository;

    Timestamp TimeNow = Time.getTimeNow();

    @Transactional(readOnly = true)
    public List<UserResponse> getUser() {
        Specification<UserModel> active = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), false);
        List<UserModel> userList = userRepository.findAll();

        return userList.stream()
                .map(userModel -> UserResponse.builder()
                        .id(userModel.getId())
                        .firstname(userModel.getFirstname())
                        .lastname(userModel.getLastname())
                        .phone(userModel.getPhone())
                        .email(userModel.getEmail())
                        .createdAt(userModel.getCreatedAt())
                        .modifiedAt(userModel.getModifiedAt())
                        .deleted(userModel.getDeleted())
                        .build())
                .collect(Collectors.toList());
    }



    @Transactional
    public String save(UserRequest bReq) {

        if (userRepository.findByUsername(bReq.getUsername()).isPresent()) {
            return"Username already exist";
        }

        if (userRepository.findByEmail(bReq.getEmail()).isPresent()) {
            return "Email already exists";
        }

        if (userRepository.findByPhone(bReq.getPhone()).isPresent()) {
            return "Phone already exists";
        }

        UserModel userModel = new UserModel();
        userModel.setFirstname(bReq.getFirstname());
        userModel.setLastname(bReq.getLastname());
        userModel.setPhone(bReq.getPhone());
        userModel.setEmail(bReq.getEmail());
        userModel.setPassword(BCrypt.hashpw(bReq.getPassword(), BCrypt.gensalt()));
        userModel.setUsername(bReq.getUsername());
        userModel.setCreatedAt(TimeNow);
        userModel.setDeleted(false);
        userRepository.save(userModel);

        return "Insert Success";
    }
}