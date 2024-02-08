package org.example;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CountingSameSymbolsService {
    public List<SymbolFrequency> countSameSymbols(String line) {
        if (line == null) {
            throw new IllegalArgumentException("String is null");
        }
        return line.chars().mapToObj((int c) -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(e -> new SymbolFrequency(e.getKey(), e.getValue()))
                .toList();
    }
}