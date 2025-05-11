package fr.esgi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CheckPointFilterTest {

    private CheckPointFilter checkPointFilter;
    private ServletRequest request;
    private ServletResponse response;
    private FilterChain chain;

    @BeforeEach
    void setUp() {
        checkPointFilter = new CheckPointFilter();
        request = mock(ServletRequest.class);
        response = mock(ServletResponse.class);
        chain = mock(FilterChain.class);
    }

    @Test
    void testDoFilterAddsAttributeAndProceeds() throws Exception {
        checkPointFilter.doFilter(request, response, chain);

        // Verify that request attribute is set
        verify(request).setAttribute(eq("dateHeureDebut"), anyLong());

        // Verify filter chain continues
        verify(chain).doFilter(request, response);
    }
}
