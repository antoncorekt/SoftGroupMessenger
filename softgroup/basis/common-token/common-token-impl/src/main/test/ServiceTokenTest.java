import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.token.api.TokenException;
import com.softgroup.common.token.impl.service.TokenService;
import org.hamcrest.CoreMatchers;
import org.jose4j.jwt.JwtClaims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by anton on 01.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTokenTest {

        @InjectMocks
        private TokenService serviceToken;

        @Test
        public void createDeviceTokenTest() throws TokenException{
            String s = serviceToken.createDeviceToken("testID", "testDevice");

            JwtClaims res = serviceToken.getClaimsFromToken(s);

            assertThat(res.getClaimsMap().get("userID"), CoreMatchers.<Object>is("testID" ));
            assertThat(res.getClaimsMap().get("deviceID"), CoreMatchers.<Object>is("testDevice" ));
            assertThat(res.getClaimsMap().get("type"), is("deviceToken" ));
        }

        @Test
        public void createSessionTokenTest() throws TokenException{
            String s = serviceToken.createSessionToken("testID", "testDevice");

            JwtClaims res = serviceToken.getClaimsFromToken(s);

            assertThat(res.getClaimsMap().get("userID"), is("testID" ));
            assertThat(res.getClaimsMap().get("deviceID"), is("testDevice" ));
            assertThat(res.getClaimsMap().get("type"), is("sessionToken" ));
        }

        @Test
        public void getRoutedDataTest() throws TokenException{
            String deviceToken = serviceToken.createDeviceToken("DtestID", "DtestDevice");
            String sessionToken = serviceToken.createSessionToken("StestID", "StestDevice");

            RoutedData routedData = serviceToken.getRoutedData(sessionToken);

            assertThat(routedData.getDeviceID(), is("StestDevice"));
            assertThat(routedData.getUserID(), is("StestID"));
        }



    }
