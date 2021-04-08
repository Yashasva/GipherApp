package com.stackroute.giphy.test.service;

import com.stackroute.giphy.exception.UserAlreadyExistsException;
import com.stackroute.giphy.exception.UserNotFoundException;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.repository.UserAutheticationRepository;
import com.stackroute.giphy.service.UserAuthenticationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//import java.util.Date;
import java.util.Optional;

public class UserAuthenticationServiceTest {

    @Mock
    private UserAutheticationRepository autheticationRepository;

    private User user;
    @InjectMocks
    private UserAuthenticationServiceImpl authenticationService;

    Optional<User> optional;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId("Jhon123");
        user.setUserName("Jhon123");
        user.setEmailId("john123@gmail.com");
        user.setUserPassword("123456");
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Register User", true, flag);

    }


    @Test(expected = UserAlreadyExistsException.class)
    public void testSaveUserFailure() throws UserAlreadyExistsException {

        Mockito.when(autheticationRepository.findById("Jhon123")).thenReturn(optional);
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);

    }

    @Test
    public void testFindByUserIdAndPassword() throws UserNotFoundException {
        Mockito.when(autheticationRepository.findByUserIdAndUserPassword("Jhon123", "123456")).thenReturn(user);
        User fetchedUser = authenticationService.findByUserIdAndPassword("Jhon123", "123456");
        Assert.assertEquals("Jhon123", fetchedUser.getUserId());
    }
}
