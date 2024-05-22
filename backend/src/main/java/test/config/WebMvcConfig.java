package test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**") // 匹配所有的路由
                .excludePathPatterns("/session", "/user"); // 排除不需要拦截的路由，如登录和注册页面

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/**是静态映射， file:/root/images/是文件在服务器的路径
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:C:\\Users\\Lenovo\\IdeaProjects\\demoSpring\\src\\main\\resources\\static\\images\\");
    }

}
