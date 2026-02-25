package com.example.HMovie.repository;

import com.example.HMovie.dto.identity.TokenExchangeParam;
import com.example.HMovie.dto.identity.TokenExchangeResponse;
import com.example.HMovie.dto.identity.UserCreationParam;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "identity-client", url = "${idp.url}")
public interface IdentityClient {
    @PostMapping(
            value = "/realms/HMovie/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenExchangeResponse exchangeToken(@QueryMap TokenExchangeParam param);

    @PostMapping(value = "/admin/realms/HMovie/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUser(@RequestHeader("authorization") String token, @RequestBody UserCreationParam param);

    @DeleteMapping(value = "/admin/realms/HMovie/users/{userId}")
    ResponseEntity<?> deleteUser(@RequestHeader("authorization") String token, @PathVariable("userId") String userId);

    @PutMapping(value = "/admin/realms/HMovie/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateUser(@RequestHeader("authorization") String token, @PathVariable("userId") String userId, @RequestBody UserCreationParam param);
}
