package org.inspection.nationalk1.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
@Configuration
public class PropertiesConfig {

    @Autowired
    private Environment env;

    @Bean(name = "propertiesFactory")
    public PropertiesFactoryBean propertiesFactoryBean(){
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(env.getProperty("url.properties.location")));
        return propertiesFactoryBean;
    }
}
