package app.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@MapperScan(value = "app.example.demo.Dao")
public class DemoApplication {

    @RestController
    public class RootRedirectController {
        @GetMapping("/")
        public void redirect(HttpServletResponse response) throws Exception {
            response.sendRedirect("/dist/index.html");
        }
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize(DataSize.parse("30960KB")); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("309600KB"));
        return factory.createMultipartConfig();
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
