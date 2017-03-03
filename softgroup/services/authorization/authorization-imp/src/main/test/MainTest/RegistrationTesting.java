package MainTest;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.impl.handler.RegistrationRequestHandler;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by anton on 28.02.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RegistrationTesting {

    @Configuration
    public static class TestCfg{

        @Bean
        public RegistrationRequestHandler registrationRequestHandler()
        {
            return new RegistrationRequestHandler();
        }
    }

    @Autowired
    private RegistrationRequestHandler registrationRequestHandler;
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

        System.out.println(res.getData().getRegistrationRequestUuid());

        // todo print testing data
    }
}
