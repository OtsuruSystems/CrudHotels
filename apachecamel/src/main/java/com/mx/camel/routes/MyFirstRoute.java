package com.mx.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("timer:my-first-timer")
		.log("${body}")
		.transform().constant("Logs constant")
		.log("${body}")
		.to("log:my-first log");
		
	}
}
