package com.simple.rest.app.service.calculations;

import com.simple.rest.app.model.UserDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserCalculationsServiceImplTests {

    @InjectMocks
    private UserCalculationsServiceImpl cut;

    @ParameterizedTest
    @MethodSource
    public void calculate_ReturnsCalculatedValue(
            int followerCount,
            int publicReposCount,
            double expectedCalculations
    ) {
        // given
        var userDTO = getTestUserDTO(followerCount, publicReposCount);

        //when
        var actualCalculations = cut.calculate(userDTO);

        //then
        assertEquals(expectedCalculations, actualCalculations);
    }

    private UserDTO getTestUserDTO(int followerCount, int publicReposCount) {
        var userDTO = new UserDTO();
        userDTO.setId(123);
        userDTO.setLogin("testLogin");
        userDTO.setFollowersCount(followerCount);
        userDTO.setPublicReposCount(publicReposCount);
        return userDTO;
    }

    private static Stream<Arguments> calculate_ReturnsCalculatedValue() {
        return Stream.of(
                Arguments.of(2, 5, 21f),
                Arguments.of(6, 0, 2f),
                Arguments.of(0, 5, Double.POSITIVE_INFINITY),
                Arguments.of(0, 0, Double.POSITIVE_INFINITY)
        );
    }
}
