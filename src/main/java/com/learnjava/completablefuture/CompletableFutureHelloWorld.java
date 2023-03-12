package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {
    private HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public CompletableFuture<String> helloWorldCompletableFuture(){
         return CompletableFuture.supplyAsync(() -> hws.helloWorld())
                 .thenApply(String::toUpperCase);
    }

    public String helloworld_approach1(){
        String hello = hws.hello();
        String world = hws.world();
        return hello + world;
    }

    public String helloworld_multiple_async_calls(){

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        String result =  hello.thenCombine(world , (s, s2) -> s+s2)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return result;
    }

    public String helloworld_3_async_calls(){

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        });

        String result =  hello.thenCombine(world , (s, s2) -> s+s2)
                .thenCombine(hicompletableFuture, (previous, current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        HelloWorldService hws = new HelloWorldService();
        CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenApply(String::toUpperCase)
                .thenAccept(result -> {
                   log("Result is " + result);
                })
                .join();
        ;
        log("Done !");
        //delay(2000);
    }
}
