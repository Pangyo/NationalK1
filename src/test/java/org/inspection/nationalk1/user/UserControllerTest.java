//package org.inspection.nationalk1.user;
//
//import org.inspection.nationalk1.Application;
//import org.inspection.nationalk1.controller.UserController;
//import org.inspection.nationalk1.domain.User;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.nio.charset.Charset;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//
//
///**
// * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 20..
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Application.class})
//@WebAppConfiguration
//public class UserControllerTest {
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    ObjectMapper mapper = new ObjectMapper();
//
//    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
//            MediaType.APPLICATION_JSON.getSubtype(),
//            Charset.forName("utf8"));
//
//    @Before
//    public void setUp() throws  Exception {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//    }
//    @Test
//    public void testCreate() throws Exception {
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post(UserController.URL_USER_CREATE)
//                .contentType(contentType)
//                .content(mapper.writeValueAsString(new User("박광용"))))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }
//
//}
