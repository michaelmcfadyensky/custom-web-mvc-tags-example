package com.example.customtagsmetricsdemo.config;

import com.example.customtagsmetricsdemo.web.MyWebMvcTagsProvider;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsContributor;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WebFilterConfig {

    @Bean
    public WebMvcTagsProvider webMvcTagsProvider(MetricsProperties properties, List<WebMvcTagsContributor> contributors) {
        return new MyWebMvcTagsProvider(properties.getWeb().getServer().getRequest().isIgnoreTrailingSlash(), contributors);
    }
}
