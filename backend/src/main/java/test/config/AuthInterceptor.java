package test.config;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import test.constant.UserRole;
import test.tag.AdminRequired;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 在请求处理之前进行拦截
        // 检查用户是否已经登录
        String username = "";
        String role = "";
        String code = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 遍历 Cookie 数组
            for (Cookie cookie : cookies) {
                // 根据 Cookie 名称获取对应的值
                if ("username".equals(cookie.getName())) {
                     username = cookie.getValue();
                } else if ("role".equals(cookie.getName())) {
                     role = cookie.getValue();
                }else if("code".equals(cookie.getName())){
                    code =cookie.getValue();
                }
            }
        }
        System.out.println(username);
        System.out.println(code);
        System.out.println(role);
        if (username.isBlank() || !DigestUtils.sha256Hex(username + role+ UserRole.PREFIX_CODE).equals(code) ) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Login validate failed,please re-login   ");
            // 如果未登录，重定向到登录页面或者返回未登录的错误信息
            return false; // 表示请求被拦截，不再继续处理
        }

        if (!UserRole.ADMIN.equals(role)) {
            if (handler instanceof HandlerMethod handlerMethod) {
                // 检查方法上是否存在 AdminRequired 注解
                if (handlerMethod.hasMethodAnnotation(AdminRequired.class)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("You do not have permission to access this resource.");
                    return false; // 表示请求被拦截，不再继续处理
                }
            }
        }

        System.out.println("true");
        return true;
    }
}
