package spring.boot.structure.services;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TrafficServiceTest {

    String rediskeyPattern = "net.funpodium.service.traffic:ip2country:%s";
    //@Mock

    //@InjectMocks


//    private static Stream<Arguments> createTestRule() {
//        return Stream.of(
//                Arguments.of("0.0.0.0", 0L, "-", true),
//                Arguments.of("255.255.255.255", 4294967295L, "-", true),
//                Arguments.of("202.39.9.79", 3391555919L, "TW", false));
//    }
//

//    @BeforeEach
//    public void setup() {
//        valueOperations = Mockito.mock(ValueOperations.class);
//    }
//
//    @ParameterizedTest
//    @MethodSource("createRedisCountryShort")
//    public void testCheckRule_findCountryUnknownInRedis_ReturnTrue(String ip, String countryShort) {
//
//        //Arrange
//        String redisKey = String.format(rediskeyPattern, ip);
//
//        when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
//        when(valueOperations.get(redisKey)).thenReturn(countryShort);
//
//
//        CheckRuleResource checkRuleResource = new CheckRuleResource();
//        checkRuleResource.setIp(ip);
//
//        //Act
//        boolean actual = trafficService.checkRule(checkRuleResource);
//
//        //Assertion
//        assertTrue(actual);
//    }


}