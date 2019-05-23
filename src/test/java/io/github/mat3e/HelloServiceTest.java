package io.github.mat3e;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {

    private HelloService SUT = new HelloService();

    @Test
    public void testNullPrepareGreetingReturnsGreetingWithFallbackValue() throws Exception {
       String result=SUT.prepareGreeting(null);
       assertEquals("Hello "+HelloService.FALLBACK_NAME+"!",result);
    }
    @Test
    public void testNamePrepareGreetingReturnsGreetingWithName() throws Exception {
        String test="test";
        String result=SUT.prepareGreeting(test);
        assertEquals("Hello "+test+"!",result);
    }

}
