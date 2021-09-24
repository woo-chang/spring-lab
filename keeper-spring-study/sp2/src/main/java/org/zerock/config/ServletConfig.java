package org.zerock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.beans.PropertyEditorSupport;
import java.io.IOError;
import java.io.IOException;

@EnableWebMvc
@ComponentScan(basePackages = {"org.zerock.controller", "org.zerock.exception"})
public class ServletConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        // 10MB
        resolver.setMaxUploadSize(1024 * 1024 * 10);

        // 2MB
        resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);

        // 1MB
        resolver.setMaxInMemorySize(1024 * 1024);

        // temp upload
        resolver.setUploadTempDir(new FileSystemResource("/Users/woochang/Desktop/무제 폴더"));

        resolver.setDefaultEncoding("UTF-8");

        return resolver;
    }
}
