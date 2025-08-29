package co.com.crediya.models.response;

import co.com.crediya.models.transaction.StateTransaction;
import co.com.crediya.models.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.util.List;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDTO (
    Transaction transaction,
    StateTransaction estado,
    List<String> errores
) {
}
