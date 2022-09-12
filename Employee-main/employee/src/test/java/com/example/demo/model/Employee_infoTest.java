package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Employee_infoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Employee_info#Employee_info()}
     *   <li>{@link Employee_info#setEmailId(String)}
     *   <li>{@link Employee_info#setFirstName(String)}
     *   <li>{@link Employee_info#setId(long)}
     *   <li>{@link Employee_info#setLastName(String)}
     *   <li>{@link Employee_info#getEmailId()}
     *   <li>{@link Employee_info#getFirstName()}
     *   <li>{@link Employee_info#getId()}
     *   <li>{@link Employee_info#getLastName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Employee_info actualEmployee_info = new Employee_info();
        actualEmployee_info.setEmailId("42");
        actualEmployee_info.setFirstName("Jane");
        actualEmployee_info.setId(123L);
        actualEmployee_info.setLastName("Doe");
        assertEquals("42", actualEmployee_info.getEmailId());
        assertEquals("Jane", actualEmployee_info.getFirstName());
        assertEquals(123L, actualEmployee_info.getId());
        assertEquals("Doe", actualEmployee_info.getLastName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Employee_info#Employee_info(String, String, String)}
     *   <li>{@link Employee_info#setEmailId(String)}
     *   <li>{@link Employee_info#setFirstName(String)}
     *   <li>{@link Employee_info#setId(long)}
     *   <li>{@link Employee_info#setLastName(String)}
     *   <li>{@link Employee_info#getEmailId()}
     *   <li>{@link Employee_info#getFirstName()}
     *   <li>{@link Employee_info#getId()}
     *   <li>{@link Employee_info#getLastName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Employee_info actualEmployee_info = new Employee_info("Jane", "Doe", "42");
        actualEmployee_info.setEmailId("42");
        actualEmployee_info.setFirstName("Jane");
        actualEmployee_info.setId(123L);
        actualEmployee_info.setLastName("Doe");
        assertEquals("42", actualEmployee_info.getEmailId());
        assertEquals("Jane", actualEmployee_info.getFirstName());
        assertEquals(123L, actualEmployee_info.getId());
        assertEquals("Doe", actualEmployee_info.getLastName());
    }
}

