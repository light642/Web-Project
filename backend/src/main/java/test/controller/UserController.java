package test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import test.constant.UserRole;
import test.entity.User;
import test.mapper.UserMapper;
import test.service.UserService;

@RestController
public class UserController {
    UserService userService;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/session")
    public boolean login(User user, HttpSession session, HttpServletResponse response) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).eq("password", user.getPassword());
        if (!userService.exists(queryWrapper)) return false;
        User user1 = userMapper.selectOne(queryWrapper);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
        Cookie cookie = new Cookie("username", user.getUsername());
        Cookie roleCookie = new Cookie("role", user1.getRole());
        response.addCookie(cookie);
        response.addCookie(roleCookie);
        // 混淆加密,防止role或者username被直接修改
        String code = DigestUtils.sha256Hex(user.getUsername() + user1.getRole()+ UserRole.PREFIX_CODE);
        Cookie codeCookie = new Cookie("code", code);
        System.out.println(code);
        System.out.println(user.getUsername() + user1.getRole()+ UserRole.PREFIX_CODE);
        response.addCookie(codeCookie);
        return true;
    }

    @PostMapping("/user")
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
