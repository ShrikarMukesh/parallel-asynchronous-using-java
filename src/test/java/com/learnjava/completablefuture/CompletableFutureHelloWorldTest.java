package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.CommonUtil;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();

    CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorldCompletableFuture() {
        //given

        //when
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorldCompletableFuture();

        //then
        completableFuture.thenAccept(s -> {
            assertEquals("HELLO WORLD", s);
            assertEquals(11,s.length());
        }).join();
    }

    @Test
    void helloworld_multiple_async_calls(){
        //given
        //when
        String helloWorld = completableFutureHelloWorld.helloworld_multiple_async_calls();
        //then
        assertEquals("HELLO WORLD!" , helloWorld);
    }

    @Test
    void helloworld_3_async_calls(){
        //given
        //when
        String helloWorld = completableFutureHelloWorld.helloworld_3_async_calls();
        //then
        assertEquals("HELLO WORLD!HI COMPLETABLEFUTUERE" , helloWorld);
    }

    @Test
    void helloworld_thenCOmpose(){
        //given

        CommonUtil.startTimer();
        //when
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld_thenCOmpose();

        //then
        completableFuture.thenAccept(s -> {
            assertEquals("HELLO WORLD!", s);

        }).join();

        CommonUtil.timeTaken();
    }
}