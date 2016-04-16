package org.inspection.nationalk1.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
@Component
public class RestApiUtils {
    private HttpClient httpClient;
    private RestTemplate restTemplate;
    @Autowired @Qualifier(value = "propertiesFactory") private Properties propertiesFactory;

    private final String SERVICE_KEY_TOKEN="[servicekey]";
    private String domain;
    private String serviceKey;

    @PostConstruct
    public final void setUp() {
        httpClient = HttpClients.createDefault();
        restTemplate = new RestTemplate();
        domain = propertiesFactory.getProperty("public.data.url");
        serviceKey = propertiesFactory.getProperty("public.data.service.key");
    }

    public String getExcute(String key) {
        return getExcute(key, null);
    }

    public String getExcute(String key, Map<String, String> paramMap) {

        String url = domain + expand(propertiesFactory.getProperty(key), paramMap);
        HttpUriRequest request = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if(response.getEntity() != null) {
                    String result =  IOUtils.toString(response.getEntity().getContent());
                    System.out.println(result);
                    return result;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String expand(String text, Map<String, String> paramMap) {
        String result = text;
        result = StringUtils.replace(result, SERVICE_KEY_TOKEN, serviceKey);
        if(paramMap != null) {
            for(String key : paramMap.keySet()) {
                result = StringUtils.replace(result, "[" + key + "]", paramMap.get(key));
            }
        }
        return result;
    }
}
