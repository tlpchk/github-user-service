package com.simple.rest.app.service.rest;

import com.simple.rest.app.exception.LoginCounterNotFoundException;
import com.simple.rest.app.model.LoginCounter;
import com.simple.rest.app.repository.LoginCounterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LoginCounterServiceTests {

    @Mock
    private LoginCounterRepository loginCounterRepository;

    @InjectMocks
    private LoginCounterService cut;

    private LoginCounter counter;

    @BeforeEach
    void init() {
        counter = testLoginCounter();
        mockFindById(counter);
    }

    private LoginCounter testLoginCounter() {
        return new LoginCounter("user1", 10);
    }

    private void mockFindById(LoginCounter counter) {
        var login = counter.getLogin();
        when(loginCounterRepository.findById(login)).thenReturn(Optional.of(counter));
        when(loginCounterRepository.findById(not(eq(login)))).thenReturn(Optional.empty());
    }

    @Test
    public void findById_ReturnsUserWhenItExists() {
        // given
        var userLogin = counter.getLogin();

        // when
        var actualCounter = cut.findById(userLogin);

        // then
        assertEquals(counter, actualCounter);
    }

    @Test
    public void findById_RaiseNotFoundExceptionWhenWhenUserNotExists() {
        // given
        var userLogin = "NON_EXISTING_USER";

        // when - then
        assertThrows(LoginCounterNotFoundException.class, () -> cut.findById(userLogin));
    }

    @Test
    public void incrementCounter_IncrementsRequestCountByOneWhenItExists() {
        // given
        var userLogin = counter.getLogin();
        var userRequestCount = counter.getRequestCount();

        // when
        cut.incrementCounter(userLogin);

        // then
        var actualRequestCount = getSavedRequestCount();
        verify(loginCounterRepository, times(1)).findById(userLogin);
        assertEquals(userRequestCount + 1, actualRequestCount);
    }

    @Test
    public void incrementCounter_CreatesNewCounterAndIncrementRequestCountByOneWhenItNotExists() {
        // given
        var userLogin = "NON_EXISTING_USER";

        // when
        cut.incrementCounter(userLogin);

        // then
        var actualRequestCount = getSavedRequestCount();
        verify(loginCounterRepository, times(1)).findById(userLogin);
        assertEquals(1, actualRequestCount);
    }

    private long getSavedRequestCount() {
        ArgumentCaptor<LoginCounter> argCaptor = ArgumentCaptor.forClass(LoginCounter.class);
        verify(loginCounterRepository).save(argCaptor.capture());
        return argCaptor.getValue().getRequestCount();
    }
}
