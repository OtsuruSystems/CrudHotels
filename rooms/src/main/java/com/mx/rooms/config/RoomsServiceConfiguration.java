package com.mx.rooms.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="rooms")
@Data
public class RoomsServiceConfiguration {
	private String msg;
	private String buildVersion;
	private Map<String, String> mailDetails;

}
