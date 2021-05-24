package org.example.znakapi.service;

import lombok.extern.java.Log;
import org.example.znakapi.enums.RestOperationResult;
import org.example.znakapi.model.StoreDocRequest;
import org.example.znakapi.model.StoreDocResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log
@Service
public class ZnakApiService {

    public ResponseEntity<StoreDocResponse> processDoc(StoreDocRequest request) {
        log.info("success processed request = ".concat(request.toString()));
        return new ResponseEntity<>(new StoreDocResponse(RestOperationResult.SUCCESS), HttpStatus.OK);
    }
}
