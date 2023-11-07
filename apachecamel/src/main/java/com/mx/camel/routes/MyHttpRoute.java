package com.mx.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyHttpRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration().host("localhost").port(8082);
		
		from("timer:rest-api-consumer?period=10000")
		.log("${body}")
		.to("rest:get:/reservations")
		.log("${body}");
		
	}

}
