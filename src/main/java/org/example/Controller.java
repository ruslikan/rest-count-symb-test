package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    private final CountingSameSymbolsService countingSameSymbolsService;

    public Controller(CountingSameSymbolsService countingSameSymbolsService) {
        this.countingSameSymbolsService = countingSameSymbolsService;
    }

    @GetMapping("/counting-symbols")
    public SymbolCountResponse myClassEndpoint(@RequestParam("input") String input) {
        return new SymbolCountResponse(countingSameSymbolsService.countSameSymbols(input));
    }

}