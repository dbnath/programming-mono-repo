package com.git.amc.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AppStartupTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void appStartup() {
        assertFalse(thrown.isAnyExceptionExpected());
    }
}
