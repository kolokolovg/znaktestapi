package org.example.znakapi.rest;


import org.example.znakapi.exception.RestErrorHandler;
import org.example.znakapi.model.Product;
import org.example.znakapi.model.StoreDocRequest;
import org.example.znakapi.model.StoreDocResponse;
import org.example.znakapi.service.ZnakApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@EnableAutoConfiguration
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ImportAutoConfiguration(RestErrorHandler.class)
@SpringBootTest(classes = {ZnakApiController.class, ZnakApiService.class})
class ZnakApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<StoreDocResponse> jsonResponse;
    @Autowired
    private JacksonTester<StoreDocRequest> jsonRequest;

    @Test
    void checkGoodPath() throws Exception {
        var req = new StoreDocRequest("123534251", "648563524",
                List.of(new Product("milk", "2364758363546"),
                        new Product("water", "3656352437590")));
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(1L, "success processing")).getJson(),
                response.getContentAsString());
    }

    @Test
    void checkBadProductCodeSize() throws Exception {
        var req = new StoreDocRequest("123534251", "648563524",
                List.of(new Product("milk", "236475836354"),
                        new Product("water", "36563524375901")));
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(2L, "{products[0].code=size must be between 13 and 13, products[1].code=size must be between 13 and 13}")).getJson(),
                response.getContentAsString());
    }

    @Test
    void checkEmptyProductList() throws Exception {
        var req = new StoreDocRequest("123534251", "648563524", null);
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(2L, "{products=must not be empty}")).getJson(),
                response.getContentAsString());
    }

    @Test
    void checkEmptySeller() throws Exception {
        var req = new StoreDocRequest(null, "648563524",
                List.of(new Product("milk", "2364758363546"),
                        new Product("water", "3656352437590")));
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(2L, "{seller=must not be empty}")).getJson(),
                response.getContentAsString());
    }

    @Test
    void checkEmptyCustomer() throws Exception {
        var req = new StoreDocRequest("123534251", null,
                List.of(new Product("milk", "2364758363546"),
                        new Product("water", "3656352437590")));
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(2L, "{customer=must not be empty}")).getJson(),
                response.getContentAsString());
    }

    @Test
    void checkEmptyAllFields() throws Exception {
        var req = new StoreDocRequest(null, null, null);
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(2L, "{seller=must not be empty, customer=must not be empty, products=must not be empty}")).getJson(),
                response.getContentAsString());
    }

    @Test
    void checkBadMediaType() throws Exception {
        var req = new StoreDocRequest("123534251", "648563524",
                List.of(new Product("milk", "2364758363546"),
                        new Product("water", "3656352437590")));
        var request = jsonRequest.write(req).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                post("/storedoc/")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(request))
                .andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonResponse.write(new StoreDocResponse(2L, "request parameters error")).getJson(),
                response.getContentAsString());
    }
}