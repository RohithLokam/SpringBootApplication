package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.SearchData.Data;
import com.example.demo.SearchData.DataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DataControllerTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DataService dataService;

    @BeforeEach
    public void setup() {
        dataService = new DataService();
    }

    @Test
    public void testSearching() {
        Data data = new Data();
        data.setUsername("testUser");
        data.setPassword("testPassword");

        List<Map<String, Object>> table = new ArrayList<>();
        Map<String, Object> testData = new HashMap<>();
        testData.put("role", "A");
        table.add(testData);

        Mockito.when(jdbcTemplate.queryForList(Mockito.anyString(), Mockito.any(Object[].class)))
               .thenReturn(table);

        String result = dataService.searching(data);

        assertEquals("Data inserted", result);
    }

}

