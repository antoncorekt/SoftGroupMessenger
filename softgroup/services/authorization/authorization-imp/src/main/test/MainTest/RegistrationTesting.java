package MainTest;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.impl.handler.RegistrationRequestHandler;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by anton on 28.02.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistrationTesting {

    @Configuration
    public static class TestCfg{

        @Bean(name = "lol")
        public RegistrationRequestHandler registrationRequestHandler()
        {
            return new RegistrationRequestHandler();
        }
    }

    @InjectMocks
    private RegistrationRequestHandler registrationRequestHandler;

    @Before
    public void init(){

    }

    @org.junit.Test
    public void test1(){
        Request<RegisterRequest> request = new Request<>();

        // Handler handler = Mockito.mock(Handler.class);

        ActionHeader actionHeader = new ActionHeader();
        actionHeader.setUuid(UUID.randomUUID().toString());
        actionHeader.setType("authorization");
        actionHeader.setCommand("register");

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setDeviceId("devise_id");
        registerRequest.setLocaleCode("uk_UA");
        registerRequest.setPhoneNumber("+3809912345601");

        request.setHeader(actionHeader);
        request.setData(registerRequest);

        Response<RegisterResponse> res = registrationRequestHandler.handleWork(request);

        
    }

    @Test
    public void testGetName(){
        assert 2==2;
    }


}
