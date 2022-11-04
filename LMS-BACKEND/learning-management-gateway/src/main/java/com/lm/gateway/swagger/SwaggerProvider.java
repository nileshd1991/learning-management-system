package com.lm.gateway.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@Slf4j
public class SwaggerProvider implements SwaggerResourcesProvider {
    
    public static final String API_URI = "/v3/api-docs";
    
    public static final String API_GATEWAY_PREDICATE = "learning-management-gateway";
    
    private final RouteDefinitionLocator routeLocator;
    
    public SwaggerProvider(RouteDefinitionLocator routeLocator) {
        this.routeLocator = routeLocator;
    }
    
    @Override
    public List<SwaggerResource> get() {
        String gatewayPredicate = "/" + API_GATEWAY_PREDICATE;
        List<SwaggerResource> resources = new ArrayList<>();
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            String resourceName = routeDefinition.getId();
            String location = routeDefinition.getPredicates().get(0).getArgs().get("_genkey_0").replace("/**", API_URI);

            if (location.contains(gatewayPredicate)) {
                location = location.replace(gatewayPredicate, "");
            }
            resources.add(swaggerResource(resourceName, location));
        });
        
        return resources;
    }
    
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;    
    }
}