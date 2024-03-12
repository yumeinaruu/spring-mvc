package mvc.com.controller;

import mvc.com.model.User;
import mvc.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller //mvc схемы где есть страницы
@RequestMapping("/user") //все методы этого контроллера начинаются с /user
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return users.isEmpty() ? "empty" : "get_all_users";
    }

    @GetMapping("/hello") //http GET method
    public String helloPage() {
        boolean ok = new Random().nextBoolean();
        return ok ? "success" : "failure";
    }
}
