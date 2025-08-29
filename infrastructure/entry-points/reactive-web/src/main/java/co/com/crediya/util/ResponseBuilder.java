package co.com.crediya.util;

import co.com.crediya.models.response.ResponseDTO;
import co.com.crediya.models.transaction.StateTransaction;
import co.com.crediya.models.transaction.Transaction;
import java.util.List;

public class ResponseBuilder {
    
    public static ResponseDTO success(Transaction transaction) {
        return ResponseDTO.builder()
                .transaction(transaction)
                .estado(StateTransaction.SUCCESS)
                .build();
    }
    
    public static ResponseDTO error(Transaction transaction, List<String> errors) {
        return ResponseDTO.builder()
                .transaction(transaction)
                .estado(StateTransaction.ERROR)
                .errores(errors)
                .build();
    }

    public static ResponseDTO error(List<String> errors) {
        return ResponseDTO.builder()
            .estado(StateTransaction.ERROR)
            .errores(errors)
            .build();
    }
}