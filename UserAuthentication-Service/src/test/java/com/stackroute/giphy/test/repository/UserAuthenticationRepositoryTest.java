package com.stackroute.giphy.test.repository;

import com.stackroute.giphy.model.User;
import com.stackroute.giphy.repository.UserAutheticationRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class UserAuthenticationRepositoryTest {

    @Autowired
    private UserAutheticationRepository autheticationRepository;

    private User user;


    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserId("Jhon123");
        user.setUserName("Jhon123");
        user.setEmailId("john123@gmail.com");
        user.setUserPassword("123456");

    }

    @After
    public void tearDown() throws Exception {
        autheticationRepository.deleteAll();
    }
    @Test
    public void registerUserTest() {

    	autheticationRepository.save(user);
        User fetcheduser = autheticationRepository.findById("Jhon123").get();
        Assert.assertEquals(user.getUserId(), fetcheduser.getUserId());

    }

    @Test
    public void testRegisterUserSuccess() {
        autheticationRepository.save(user);
        User object = autheticationRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }

    @Test
    public void testLoginUserSuccess() {
        autheticationRepository.save(user);
        User object = autheticationRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }
}
