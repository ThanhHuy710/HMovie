package com.example.HMovie.exception;

import com.example.HMovie.dto.identity.KeyCloakError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Component
@Slf4j
public class ErrorNormalizer {
    private final ObjectMapper objectMapper;
    private final Map<String, ErrorCode> errorCodeMap;

    public ErrorNormalizer() {
        objectMapper = new ObjectMapper();
        errorCodeMap = new HashMap<>();

    }

    public AppException handleKeyCloakException(FeignException exception) {
        try {
            log.warn("Cannot complete request", exception);
            if (exception.status() == 401) {
                return new AppException(ErrorCode.USERNAME_OR_PASSWORD_INCORECT);
            }
            String content = exception.contentUTF8();
            if (!StringUtils.hasText(content)) {
                return new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
            }
            
            var response = objectMapper.readValue(content, KeyCloakError.class);

            if (Objects.nonNull(response.getErrorMessage())
                    && Objects.nonNull(errorCodeMap.get(response.getErrorMessage()))) {
                return new AppException(errorCodeMap.get(response.getErrorMessage()));
            }
        } catch (JsonProcessingException e) {
            log.error("Cannot deserialize content", e);
        }

        return new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
    }
}
