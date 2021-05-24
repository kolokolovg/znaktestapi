package org.example.znakapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDocRequest {
    @NotNull
    @Size(min = 9, max = 9, message = "size must be 9")
    private String seller;
    @NotNull
    @Size(min = 9, max = 9, message = "size must be 9")
    private String customer;
    @NotEmpty
    private List<@Valid Product> products;
}
