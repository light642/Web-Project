import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.User;
import test.service.UserService;

import java.util.Map;

@RestController
public class UserController {
    UserService userService;

    @PostMapping("/login")
    public boolean login(User user, HttpSession session, HttpServletResponse response) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(Map.of("username", user.getUsername(), "password", user.getPassword()));
        if (!userService.exists(queryWrapper)) return false;
        session.setAttribute("username", user.getUsername());
        Cookie cookie = new Cookie("username", user.getUsername());
        response.addCookie(cookie);
        return true;
    }

    @PostMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @PostMapping("/register")
    public void register(User user, HttpSession session, HttpServletResponse response) {
        user.setRole("user");
        userService.save(user);
        session.setAttribute("username", user.getUsername());
        Cookie cookie = new Cookie("username", user.getUsername());
        response.addCookie(cookie);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
