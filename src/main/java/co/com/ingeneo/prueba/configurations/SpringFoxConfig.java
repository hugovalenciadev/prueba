package co.com.ingeneo.prueba.configurations;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {   
    
    private ApiKey apiKey() { 
        return new ApiKey("Bearer", "Authorization", "header"); 
    }

    private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 
    
    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes)); 
    }
    
    public Predicate<RequestHandler> customRequestHandlers() {
        return new Predicate<RequestHandler>() {

            @Override
            public boolean test(RequestHandler input) {                
                String name = input.getName();                
                if (name.equalsIgnoreCase("deliveryProduct") 
                    || name.equalsIgnoreCase("deliveryUnitMeasure")
                    || name.equalsIgnoreCase("deliveryWarehouse")
                    || input.groupName().equalsIgnoreCase("profile-controller")
                    || input.groupName().equalsIgnoreCase("basic-error-controller")
                    ) {
                    return false;
                }
                return true;
            }
            
        };
      }
  
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .securityContexts(Arrays.asList(securityContext()))
          .securitySchemes(Arrays.asList(apiKey()))
          .select()                                  
          .apis(customRequestHandlers())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}
