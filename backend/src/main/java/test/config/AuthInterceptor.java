package test.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import test.constant.UserRole;
import test.tag.AdminRequired;

import java.util.Objects;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
     public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")) return true;
        String username = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                     username = cookie.getValue();
                }
            }
        }
        if(request.getSession().getAttribute("username") == null
                || !Objects.equals(request.getSession().getAttribute("username").toString(), username)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        if (!UserRole.ADMIN.equals(request.getSession().getAttribute("role"))) {
            if (handler instanceof HandlerMethod handlerMethod) {
                if (handlerMethod.hasMethodAnnotation(AdminRequired.class)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("You do not have permission to access this resource.");
                    return false;
                }
            }
        }
        return true;
    }
}
