package com.example.demo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ResouceNotFoundExceptionTest {
    /**
     * Method under test: {@link ResouceNotFoundException#ResouceNotFoundException(String)}
     */
    @Test
    void testConstructor() {
        ResouceNotFoundException actualResouceNotFoundException = new ResouceNotFoundException("An error occurred");
        assertNull(actualResouceNotFoundException.getCause());
        assertEquals(0, actualResouceNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualResouceNotFoundException.getMessage());
        assertEquals("An error occurred", actualResouceNotFoundException.getLocalizedMessage());
    }
}

