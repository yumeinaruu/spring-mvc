package mvc.com.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import mvc.com.model.Book;
import mvc.com.model.User;
import mvc.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    private final User user;

    @Autowired
    public UserService(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    public Boolean deleteUserById(Long id) {
        return userRepository.deleteUser(id);
    }

    public Boolean createUser(String username, String password, Integer age) {
        user.setUsername(username);
        user.setUserPassword(password);
        user.setAge(age);
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.createUser(user);
    }

    public Boolean updateUser(Long id, String username, String password, Integer age) {
        Optional<User> userFromDbOptional = Optional.ofNullable(userRepository.findById(id));
        if (userFromDbOptional.isPresent()) {
            User userFromDb = userFromDbOptional.get();
            if (username != null) {
                userFromDb.setUsername(username);
            }
            if (password != null) {
                userFromDb.setUserPassword(password);
            }
            if (age != null) {
                userFromDb.setAge(age);
            }
            userFromDb.setChanged(Timestamp.valueOf(LocalDateTime.now()));
            return userRepository.updateUser(userFromDb);
        }
        return false;
    }
}
