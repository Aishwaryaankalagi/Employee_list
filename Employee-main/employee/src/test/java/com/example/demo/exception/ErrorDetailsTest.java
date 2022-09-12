package com.example.demo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ErrorDetailsTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorDetails#ErrorDetails(Date, String, String)}
     *   <li>{@link ErrorDetails#setDetails(String)}
     *   <li>{@link ErrorDetails#setMessage(String)}
     *   <li>{@link ErrorDetails#setTimestamp(Date)}
     *   <li>{@link ErrorDetails#getDetails()}
     *   <li>{@link ErrorDetails#getMessage()}
     *   <li>{@link ErrorDetails#getTimestamp()}
     * </ul>
     */
    @Test
    void testConstructor() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        ErrorDetails actualErrorDetails = new ErrorDetails(
                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), "Not all who wander are lost", "Details");
        actualErrorDetails.setDetails("Details");
        actualErrorDetails.setMessage("Not all who wander are lost");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualErrorDetails.setTimestamp(fromResult);
        assertEquals("Details", actualErrorDetails.getDetails());
        assertEquals("Not all who wander are lost", actualErrorDetails.getMessage());
        assertSame(fromResult, actualErrorDetails.getTimestamp());
    }
}

