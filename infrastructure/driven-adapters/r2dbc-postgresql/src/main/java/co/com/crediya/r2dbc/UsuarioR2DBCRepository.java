package co.com.crediya.r2dbc;

import co.com.crediya.r2dbc.entity.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioR2DBCRepository extends ReactiveCrudRepository<UsuarioEntity, Integer>
    //, ReactiveQueryByExampleExecutor<UsuarioEntity> TODO: Revisar
{

  Mono<UsuarioEntity> findByEmailOrDocumentoIdentidad(String email, String documentoIdentidad);
}
