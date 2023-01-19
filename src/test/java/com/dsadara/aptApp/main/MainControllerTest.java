package com.dsadara.aptApp.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MainControllerTest {

    @Autowired
    private MainController controller;

    @Test
    void testMain() {
        assertEquals(controller.main(), "this is main page");
    }

}
