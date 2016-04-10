package org.inspection.nationalk1.user;

import org.inspection.nationalk1.Application;
import org.inspection.nationalk1.domain.User;
import org.inspection.nationalk1.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 19..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class UserServiceTest {

    @Autowired private IUserService userService;
    private User user;

    @Before
    public void setUp() throws Exception{
        user = new User("Park Kwang Yong");
    }

    @Test
    public void testSave() throws Exception{
        userService.save(user);
        assertNotNull(userService.findByName(user.getName()));
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void testSaveUnique() throws Exception{
        userService.save(user);

    }

}
