package com.example.demo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class GlobalExceptionHandlerTest {
    /**
     * Method under test: {@link GlobalExceptionHandler#globalExceptionHandling(Exception, WebRequest)}
     */
    @Test
    void testGlobalExceptionHandling() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Exception exception = new Exception("foo");
        ResponseEntity<?> actualGlobalExceptionHandlingResult = globalExceptionHandler.globalExceptionHandling(exception,
                new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(actualGlobalExceptionHandlingResult.hasBody());
        assertTrue(actualGlobalExceptionHandlingResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualGlobalExceptionHandlingResult.getStatusCode());
        assertEquals("uri=", ((ErrorDetails) actualGlobalExceptionHandlingResult.getBody()).getDetails());
        assertEquals("foo", ((ErrorDetails) actualGlobalExceptionHandlingResult.getBody()).getMessage());
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#globalExceptionHandling(Exception, WebRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGlobalExceptionHandling2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.demo.exception.GlobalExceptionHandler.globalExceptionHandling(GlobalExceptionHandler.java:20)
        //   In order to prevent globalExceptionHandling(Exception, WebRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   globalExceptionHandling(Exception, WebRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        globalExceptionHandler.globalExceptionHandling(null, new ServletWebRequest(new MockHttpServletRequest()));
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#globalExceptionHandling(Exception, WebRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGlobalExceptionHandling3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.apache.catalina.connector.Request.getRequestURI(Request.java:2449)
        //       at org.springframework.web.context.request.ServletWebRequest.getDescription(ServletWebRequest.java:372)
        //       at com.example.demo.exception.GlobalExceptionHandler.globalExceptionHandling(GlobalExceptionHandler.java:20)
        //   In order to prevent globalExceptionHandling(Exception, WebRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   globalExceptionHandling(Exception, WebRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Exception exception = new Exception("foo");
        globalExceptionHandler.globalExceptionHandling(exception, new ServletWebRequest(new Request(new Connector())));
    }
}

