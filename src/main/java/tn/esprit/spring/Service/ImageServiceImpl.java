package tn.esprit.spring.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ImageServiceImpl implements WebMvcConfigurer {

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(byteArrayHttpMessageConverter());
        }

        @Bean
        public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
            ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
            arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
            return arrayHttpMessageConverter;
        }

        private List<MediaType> getSupportedMediaTypes() {
            List<MediaType> list = new ArrayList<>();
            list.add(MediaType.IMAGE_JPEG);
            list.add(MediaType.IMAGE_PNG);
            list.add(MediaType.APPLICATION_OCTET_STREAM);
            return list;
        }
    }
