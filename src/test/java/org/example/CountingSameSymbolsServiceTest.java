package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class CountingSameSymbolsServiceTest {
    CountingSameSymbolsService countingSameSymbols = new CountingSameSymbolsService();

    @ParameterizedTest
    @MethodSource("testsStringInputArgs")
    public void testsStringInput(String line, List<SymbolFrequency> list) {
        assertThat(countingSameSymbols.countSameSymbols(line)).usingRecursiveComparison().isEqualTo(list);
    }

    static Stream<Arguments> testsStringInputArgs() {
        return Stream.of(Arguments.of("", List.<SymbolFrequency>of()),
                Arguments.of("      ", List.of(new SymbolFrequency(' ', 6)),
                        Arguments.of("11122\"", List.of(new SymbolFrequency('1', 3),
                                List.of(new SymbolFrequency('2', 2)),
                                List.of(new SymbolFrequency('\"', 1))))));
    }

    @Test
    public void testInputNull() {
        assertThrows(IllegalArgumentException.class, () -> countingSameSymbols.countSameSymbols(null));
    }

}