package com.softgroup.test.common.router;


import com.softgroup.common.datamapper.JacksonDataMapper;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 04.03.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractRequestHandlerTest {


    private AbstractRequestHandler abstractRequestHandler;

    @Spy
    private JacksonDataMapper dataMapper;


    @Before
    public void init(){
        abstractRequestHandler = Mockito.mock(AbstractRequestHandler.class, Mockito.CALLS_REAL_METHODS);

        dataMapper = Mockito.mock(JacksonDataMapper.class);
    }


    @Test
    public void testHandle(){

    }

    @Test
    public void testHandleWork(){

        assertThat(abstractRequestHandler.handleWork(new Request()),nullValue());
        assertThat(abstractRequestHandler.handleWork(null),nullValue());

    }

    @Test
    public void testGetName(){
        assertThat(abstractRequestHandler.getName(), nullValue());

        when(abstractRequestHandler.getName()).thenReturn("name");
        assertThat(abstractRequestHandler.getName(), is("name"));
    }

}