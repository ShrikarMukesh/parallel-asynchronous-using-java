package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldUsingExceptionTest {

    @Mock
    HelloWorldService helloWorldService =  mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldUsingException futureHelloWorldUsingException;

    @Test
    void helloworld_3_async_calls() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_calls();

        //then
        assertEquals(" WORLD!HI COMPLETABLEFUTUERE" , result);

    }

    @Test
    void helloworld_3_async_2() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occured"));

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_calls();

        //then
        assertEquals("HI COMPLETABLEFUTUERE" , result);

    }

    @Test
    void helloworld_3_async_3() {

        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_calls();

        //then
        assertEquals("HELLO WORLD!HI COMPLETABLEFUTUERE" , result);

    }

    @Test
    void helloworld_3_async_calls_exceptionally() {

        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_calls_exceptionally();

        //then
        assertEquals("HELLO WORLD!HI COMPLETABLEFUTUERE" , result);

    }

    @Test
    void helloworld_3_async_calls_exceptionally_2() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occured"));

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_calls_exceptionally();

        //then
        assertEquals("HI COMPLETABLEFUTUERE" , result);

    }

    @Test
    void helloworld_3_async_whenhandle() {

        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_whenhandle();

        //then
        assertEquals("HELLO WORLD!HI COMPLETABLEFUTUERE" , result);

    }

    @Test
    void helloworld_3_async_whenhandle_2() {

        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occured"));

        //when
        String result = futureHelloWorldUsingException.helloworld_3_async_whenhandle();

        //then
        assertEquals("HI COMPLETABLEFUTUERE" , result);

    }
}