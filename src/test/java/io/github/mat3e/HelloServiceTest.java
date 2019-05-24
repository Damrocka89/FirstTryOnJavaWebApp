package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void testNullNamePrepareGreetingReturnsGreetingWithFallbackValue() throws Exception {

        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        String result = SUT.prepareGreeting(null, "-1");
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

    @Test
    public void testNamePrepareGreetingReturnsGreetingWithName() throws Exception {
        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name = "test";
        String result = SUT.prepareGreeting(name, "-1" + "");
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void testNullLangPrepareGreetingReturnsGreetingWithFallbackIdLang() throws Exception {
        LangRepository mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);
        String result = SUT.prepareGreeting(null, "abc");
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void testTextLangPrepareGreetingReturnsGreetingWithFallbackIdLang() throws Exception {
        LangRepository mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);
        String result = SUT.prepareGreeting(null, null);
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    @Test
    public void testNonExistingLangPrepareGreetingReturnsGreetingWithFallbackLang() throws Exception {
        LangRepository mockRepository = fallbackLangRepository();
        HelloService SUT = new HelloService(mockRepository);
        String result = SUT.prepareGreeting(null, "-1");
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
    }

}
