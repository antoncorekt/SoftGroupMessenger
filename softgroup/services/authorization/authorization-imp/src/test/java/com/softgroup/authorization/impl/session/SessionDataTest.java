package com.softgroup.authorization.impl.session;

import com.softgroup.authorization.impl.handler.LoginRequestHandlerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anton on 07.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SessionDataTest {
    private static Logger logger = LoggerFactory.getLogger(LoginRequestHandlerTest.class);

    @InjectMocks
    private SessionData sessionData = Mockito.mock(SessionData.class, Mockito.CALLS_REAL_METHODS);

    @Test
    public void createAuthCode() throws Exception {
        assertThat(sessionData.createAuthCode(),notNullValue());
        assertThat(sessionData.createAuthCode().length(),is(SessionData.getCountNumInCode()));

    }

}