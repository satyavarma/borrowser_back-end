package com.alacriti.microlending.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.alacriti.microlending.resource.BankResource;
import com.alacriti.microlending.resource.UserResource;

@Component
public class RestConfig extends ResourceConfig{

	public RestConfig(Class<?>... classes) {
		register(UserResource.class);
		register(BankResource.class);
		register(CORSFilter.class);
	}
	
}
