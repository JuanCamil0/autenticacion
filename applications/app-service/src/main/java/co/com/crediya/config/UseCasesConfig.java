package co.com.crediya.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.crediya.usecase",
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
    },
    useDefaultFilters = false)
/*
    The @ComponentScan's configuration above registers all use case classes as Spring beans,
    enabling dependency injection throughout the application without manual bean definitions like:
    //However it's still correct if chosen this way.
    @Bean
    public SomeUseCase someUseCase(SomeRepository someRepository) {
        return new SomeUseCase(someRepository);
    }
 */
public class UseCasesConfig {
}
