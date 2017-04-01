import com.softgroup.common.token.api.TokenException;
import com.softgroup.common.token.impl.service.ServiceToken;
import jdk.nashorn.internal.parser.Token;
import org.hamcrest.CoreMatchers;
import org.jose4j.jwt.JwtClaims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertThat;

/**
 * Created by anton on 01.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTokenTest {

        @InjectMocks
        private ServiceToken serviceToken;

        @Test
        public void createDeviceTokenTest(){

            try {
                String s = serviceToken.createDeviceToken("testID", "testDevice");

                System.out.println(s);

                JwtClaims res = serviceToken.getClaimsFromToken(s);

                assertThat(res.getClaimsMap().get("userID"), CoreMatchers.<Object>is("testID" ));
                assertThat(res.getClaimsMap().get("deviceID"), CoreMatchers.<Object>is("testDevice" ));
            }
            catch (TokenException e){
                System.out.println(e.toString());
            }
        }

        @Test
        public void lol(){
            System.out.println("hello word");
        }

    }
