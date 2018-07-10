package com.alpere.reactiveexample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

@SpringBootApplication
public class ReactiveExample1Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ReactiveExample1Application.class, args);

		HttpHandler httpHandler = toHttpHandler(getRouter());

		HttpServer
				.create("localhost", 8088)
				.newHandler(new ReactorHttpHandlerAdapter(httpHandler))
				.block();

		Thread.currentThread().join();
	}

	static RouterFunction getRouter() {
		HandlerFunction hello = request -> ServerResponse.ok().body(fromObject("Hello"));
		HandlerFunction json = request -> ServerResponse.ok().contentType(APPLICATION_JSON)
				.body(fromObject(new Hello("world")));

		return route(GET("/"), hello)
				.andRoute(GET("/json"), json);
	}
}
