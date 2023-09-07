package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        String result =  hello
                .thenCombine(world , (s, s2) -> {
                    log("thenCombine h/w");
                    return s+s2;
                })
                .thenCombine(hicompletableFuture, (previous, current)->{
                    log("thenCombine previous, current");
                    return previous+current;
                })
                .thenApply(s ->{
                    log("thenApply ");
                    return s.toUpperCase();
                })
                .join();
        timeTaken();
        return result;
    }
    /*
     @This method will be demonstrating the best way to create ExecutorService and use inside CompletableFuture
     */
    public String helloworld_3_async_calls_custom_threadpool(){

        startTimer();

        //Threads will be created based on Number of cores
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world(), executorService);
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        }, executorService);

        String result =  hello
                .thenCombine(world , (s, s2) -> {
                    log("thenCombine h/w");
                    return s+s2;
                })
                .thenCombine(hicompletableFuture, (previous, current)->{
                    log("thenCombine previous, current");
                    return previous+current;
                })
                .thenApply(s ->{
                    log("thenApply ");
                    return s.toUpperCase();
                })
                .join();
        timeTaken();
        return result;
    }
    /*
      thenCombineAsync(), thenCombineAsync() Important usage
     */

    public String helloworld_3_async_calls_log_async(){

        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        });

        String result =  hello
                .thenCombineAsync(world , (s, s2) -> {
                    log("thenCombine h/w");
                    return s+s2;
                })
                .thenCombineAsync(hicompletableFuture, (previous, current)->{
                    log("thenCombine previous, current");
                    return previous+current;
                })
                .thenApplyAsync(s ->{
                    log("thenApply ");
                    return s.toUpperCase();
                })
                .join();
        timeTaken();
        return result;
    }

    public String helloworld_3_async_calls_log_async_withExcecutorService(){

        startTimer();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world(), executorService);
        CompletableFuture<String> hicompletableFuture = CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi CompletableFutuere";
        }, executorService);

        String result =  hello
                .thenCombineAsync(world , (s, s2) -> {
                    log("thenCombine h/w");
                    return s+s2;
                }, executorService)
                .thenCombineAsync(hicompletableFuture, (previous, current)->{
                    log("thenCombine previous, current");
                    return previous+current;
                }, executorService)
                .thenApplyAsync(s ->{
                    log("thenApply ");
                    return s.toUpperCase();
                }, executorService)
                .join();
        timeTaken();
        return result;
    }

    public CompletableFuture<String>  helloWorld_thenCompose(){
        return CompletableFuture.
                supplyAsync(hws::hello)
                .thenCompose(s -> hws.worldFuture(s))
                .thenApply(String::toUpperCase);
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
