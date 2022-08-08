package classify.webserver.config;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j

public class Config implements WebMvcConfigurer{
    private String potoAdd = "file:/C:/Users/starc/Downloads/project/";
    private String thumbnail = "file:/C:/Users/starc/Downloads/project/thumbnail/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resistry){
        resistry.addResourceHandler("/img/**")
                .addResourceLocations(potoAdd);
        resistry.addResourceHandler("/img/thumbnail/**")
                .addResourceLocations(thumbnail);
    }

}
