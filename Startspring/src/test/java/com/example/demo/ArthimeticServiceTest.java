package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Basic.ArthimeticService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArthimeticServiceTest {

    private ArthimeticService arthimeticService;

    @BeforeEach
    public void setUp() {
        arthimeticService = new ArthimeticService();
    }

    @Test
    public void testAdd() {
        int result = arthimeticService.add(5, 10);
        assertEquals(15, result);
    }

    @Test
    public void testSub() {
        int result = arthimeticService.sub(10, 5);
        assertEquals(5, result);
    }

    @Test
    public void testMul() {
        int result = arthimeticService.mul(5, 10);
        assertEquals(50, result);
    }

    
}
