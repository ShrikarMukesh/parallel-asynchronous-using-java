package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorldUsingException {

    private HelloWorldService hws;

    public CompletableFutureHelloWorldUsingException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloworld_3_async_calls(){

        //startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        });

        String result =  hello
                .handle((res,e) -> {
                    log("Response is " + res);
                    if(e != null){
                        log("Exception is: " +e.getMessage());
                        return "";
                    }else{
                        return res;
                    }
                })
                .thenCombine(world , (s, s2) -> s+s2)
                .handle((res,e) -> {
                    log("Response is " + res);
                    if(e != null){
                        log("Exception after world is : " +e.getMessage());
                        return "";
                    }else{
                        return res;
                    }
                })
                .thenCombine(hicompletableFuture, (previous, current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();
       // timeTaken();
        return result;
    }

    public String helloworld_3_async_calls_exceptionally(){

        //startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        });

        String result =  hello
                .exceptionally(ex -> {
                    log("Exception is : "+ ex.getMessage());
                    return "";
                })
                .thenCombine(world , (s, s2) -> s+s2)
                .exceptionally(ex -> {
                    log("Exception after world : "+ ex.getMessage());
                    return "";
                })
                .thenCombine(hicompletableFuture, (previous, current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();
        // timeTaken();
        return result;
    }

    public String helloworld_3_async_whenhandle(){

        //startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        });

        String result =  hello
                .whenComplete((res,e) -> {
                    log("Response is " + res);
                    if(e != null){
                        log("Exception is: " +e.getMessage());
                    }
                })
                .thenCombine(world , (s, s2) -> s+s2)
                .whenComplete((res,e) -> {
                    log("Response is " + res);
                    if(e != null){
                        log("Exception after world is : " +e.getMessage());
                    }
                })
                .exceptionally(throwable -> {
                    log("Exception ater then combine is" + throwable.getMessage());
                    return "";
                })
                .thenCombine(hicompletableFuture, (previous, current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();
        // timeTaken();
        return result;
    }
}
