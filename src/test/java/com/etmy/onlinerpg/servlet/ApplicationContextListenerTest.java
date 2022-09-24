package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationContextListenerTest {
    @Mock
    ServletContextEvent servletContextEvent;
    @Mock
    ServletContext servletContext;
    @Spy
    ApplicationContextListener applicationContextListener;


    @Test
    void testContextInitialized() {
        when(servletContextEvent.getServletContext())
                .thenReturn(servletContext);

        applicationContextListener.contextInitialized(servletContextEvent);

        verify(servletContext).setAttribute(argThat("app"::equals), any(Application.class));
    }

}