package co.com.crediya;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import co.com.crediya.config.UsuarioPathProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterRest {

  @Bean
  public RouterFunction<ServerResponse> routerFunction(
      UsuarioPathProperties usuarioPathProperties, RouterHandler routerHandler) {
    return route(POST(usuarioPathProperties.users()), routerHandler::crearUsuarioListener);
  }

}
