package com.spring.template.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

@Configuration
public class FeignClientBeanConfig {

	private <T> T getFeignBuilder(Class<T> type, int connectTimeoutMillis, int readTimeoutMillis, String uri) {
		return Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).errorDecoder(null)
				.logger(new Slf4jLogger()).retryer(new Retryer.Default()).logLevel(Level.FULL)
				.options(new Request.Options(connectTimeoutMillis, TimeUnit.MILLISECONDS, readTimeoutMillis,
						TimeUnit.MILLISECONDS, true))
				.target(type, uri);
	}

}
