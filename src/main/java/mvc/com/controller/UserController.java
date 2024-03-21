package mvc.com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import mvc.com.exceptions.ValidationException;
import mvc.com.model.User;
import mvc.com.model.dto.UserCreateDto;
import mvc.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
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
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        List<User> users = userService.getAllUsers();
        modelAndView.setViewName(users.isEmpty() ? "empty" : "get_users");
        modelAndView.addObject("users", users);
        modelAndView.setStatus(HttpStatusCode.valueOf(200));
        return modelAndView;
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/{id}")//гетмаппинг проще
    @GetMapping(value = "/{id}")
    public ModelAndView getUserById(@PathVariable("id") Long id, ModelAndView modelAndView) { // аннотация если мы хотим достать что-то из url
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            modelAndView.setViewName("get_user_by_id");
            modelAndView.addObject("user", user.get());
            modelAndView.setStatus(HttpStatusCode.valueOf(200));
            return modelAndView;
        }
        modelAndView.setViewName("failure");
        modelAndView.setStatus(HttpStatusCode.valueOf(404));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String deleteByUserId(@PathVariable("id") Long id, HttpServletResponse response) {
        if (userService.deleteUserById(id)) {
            response.setStatus(204);
            return "success";
        }
        response.setStatus(409);
        return "failure";
    }


/*    @PostMapping
    public String createUser(@RequestBody User user) { //в запросе придет тело JSON
        return userService.createUser(user) ? "success" : "failure";
    }*/

    @PostMapping
    public String createUser(@ModelAttribute @Valid UserCreateDto user, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().toString());

/*            for (ObjectError error : bindingResult.getAllErrors()){
                System.out.println(error);
            }
            return "failure";*/
        }
        if (userService.createUser(user)) {
            response.setStatus(201);
            return "success";
        }
        response.setStatus(409);
        return "failure";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("id") Long id,
                             @RequestParam("age") Integer age,
                             HttpServletResponse response) {
        if (userService.updateUser(id, username, password, age)) {
            response.setStatus(204);//не добавляем информацию
            return "success";
        }
        response.setStatus(409);
        return "failure";
    }

/*    @GetMapping("/hello") //http GET method
    public String helloPage() {
        boolean ok = new Random().nextBoolean();
        return ok ? "success" : "failure";
    }*/
}
