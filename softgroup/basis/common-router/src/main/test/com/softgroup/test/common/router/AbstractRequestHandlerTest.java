package com.softgroup.test.common.router;

import com.softgroup.common.datamapper.DataMapper;
import com.softgroup.common.datamapper.JacksonDataMapper;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

        when(abstractRequestHandler.getName()).thenReturn("lol");
        dataMapper = Mockito.mock(JacksonDataMapper.class);
    }


    @Test
    public void testHandle(){


        assertThat(abstractRequestHandler.getName(), nullValue());
        assertThat(2, is(2));
    }

    @Test
    public void testHandleWork(){

    }

    public void testGetName(){

    }


}
