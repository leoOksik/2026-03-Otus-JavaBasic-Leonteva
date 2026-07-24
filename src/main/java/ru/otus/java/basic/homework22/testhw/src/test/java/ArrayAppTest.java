import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.ArrayApp;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayAppTest {

    @ParameterizedTest
    @DisplayName("methodOne returns new array with elements after the last number equal to one")
    @MethodSource("validDataForTestOne")
    void methodOne_shouldReturnCorrectArray(int[] inputArr, int[] expectedArr) {
        assertArrayEquals(expectedArr, ArrayApp.methodOne(inputArr));
    }

    static Stream<Arguments> validDataForTestOne() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 1, 2, 2}, new int[]{2, 2}),
                Arguments.of(new int[]{1, 1, 1, 3, 1}, new int[]{}),
                Arguments.of(new int[]{1}, new int[]{})
        );
    }

    @DisplayName("methodOne throws exception when input is invalid or has no element equal to one")
    @ParameterizedTest
    @MethodSource("invalidDataForTestOne")
    void methodOne_shouldThrowException(int[] inputArr, Class<? extends RuntimeException> expectedException) {
        assertThrowsExactly(expectedException, () -> ArrayApp.methodOne(inputArr));
    }

    static Stream<Arguments> invalidDataForTestOne() {
        return Stream.of(
                Arguments.of(new int[]{2, 2, 2, 2}, RuntimeException.class),
                Arguments.of(null, IllegalArgumentException.class),
                Arguments.of(new int[]{}, IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("methodTwo returns true only if array consists of ones and twos and has both")
    @MethodSource("validDataForTestTwo")
    void methodTwo_shouldReturnExpectedResult(int[] inputArr, boolean expectedResult) {
        assertEquals(expectedResult, ArrayApp.methodTwo(inputArr));
    }

    static Stream<Arguments> validDataForTestTwo() {
        return Stream.of(
                Arguments.of(new int[]{1, 2}, true),
                Arguments.of(new int[]{1, 1}, false),
                Arguments.of(new int[]{2, 2}, false),
                Arguments.of(new int[]{1, 3}, false),
                Arguments.of(new int[]{1, 2, 2, 1}, true),
                Arguments.of(new int[]{1}, false)
        );
    }

    @ParameterizedTest
    @DisplayName("methodTwo throws exception when input is invalid")
    @MethodSource("invalidDataForTestTwo")
    void methodTwo_shouldThrowException(int[] inputArr, Class<? extends RuntimeException> expectedException) {
        assertThrowsExactly(expectedException, () -> ArrayApp.methodTwo(inputArr));
    }

    static Stream<Arguments> invalidDataForTestTwo() {
        return Stream.of(
                Arguments.of(null, IllegalArgumentException.class),
                Arguments.of(new int[]{}, IllegalArgumentException.class)
        );
    }
}
