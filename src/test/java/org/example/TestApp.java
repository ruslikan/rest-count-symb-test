package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TestApp {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCountingSymbols() throws Exception{
        String contentAsString = this.mockMvc.perform(get("/counting-symbols").queryParam("input", "aaaaabcccc"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var response = objectMapper.readValue(contentAsString, SymbolCountResponse.class);
        assertThat(response).usingRecursiveComparison()
                .isEqualTo(new SymbolCountResponse(List.of(
                        new SymbolFrequency('a', 5),
                        new SymbolFrequency('c', 4),
                        new SymbolFrequency('b', 1))));
    }

    @Test
    void testEmptyInputReturnBadRequest() throws Exception{
        this.mockMvc.perform(get("/counting-symbols"))
                .andExpect(status().isBadRequest());

    }

}









