package com.mx.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MyFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:files/in")
		.log("${body}")
		.to("file:files/out");
		
	}

}
