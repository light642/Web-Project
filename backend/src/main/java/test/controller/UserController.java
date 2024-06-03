package test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        if (!userService.exists(queryWrapper)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        user = userMapper.selectOne(queryWrapper);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
        Cookie cookie = new Cookie("username", user.getUsername());
        Cookie roleCookie = new Cookie("role", user.getRole());
        response.addCookie(cookie);
        response.addCookie(roleCookie);
        // 混淆加密,防止role或者username被直接修改
        String code = DigestUtils.sha256Hex(user.getUsername() + user.getRole()+ UserRole.PREFIX_CODE);
        Cookie codeCookie = new Cookie("code", code);
        response.addCookie(codeCookie);
        return true;
    }

    @PostMapping("/user")
    public void register(User user, HttpSession session, HttpServletResponse response) {
        user.setRole("user");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        if(userService.exists(wrapper)){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        userService.save(user);
        session.setAttribute("username", user.getUsername());
        Cookie cookie = new Cookie("username", user.getUsername());
        response.addCookie(cookie);
    }
    @DeleteMapping("/session")
    public void logout(HttpSession session){
        System.out.println("logout");
        session.invalidate();
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
