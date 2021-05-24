package org.example.znakapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.znakapi.enums.RestOperationResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDocResponse {
    private Long code;
    private String result;

    public StoreDocResponse(RestOperationResult restResult) {
        this.code = restResult.getCode();
        this.result = restResult.getValue();
    }
}
