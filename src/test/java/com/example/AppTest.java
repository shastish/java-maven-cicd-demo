package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void greetingReturnsExpectedMessage() {
        assertEquals("Hello, CI/CD!", App.greeting());
    }
}
