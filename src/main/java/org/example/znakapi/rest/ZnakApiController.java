package org.example.znakapi.rest;

import org.example.znakapi.model.StoreDocRequest;
import org.example.znakapi.model.StoreDocResponse;
import org.example.znakapi.service.ZnakApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ZnakApiController {
    @Autowired
    private ZnakApiService znakApiService;

    @PostMapping(path = "/storedoc")
    public ResponseEntity<StoreDocResponse> storeDoc(@RequestBody @Valid StoreDocRequest request) {
        return znakApiService.processDoc(request);
    }
}

