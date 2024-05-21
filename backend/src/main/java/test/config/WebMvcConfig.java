package test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/**是静态映射， file:/root/images/是文件在服务器的路径
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:C:\\Users\\Lenovo\\IdeaProjects\\demoSpring\\src\\main\\resources\\static\\images\\");
    }
}
