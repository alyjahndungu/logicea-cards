package com.logicea.cards.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus, Object data) {
        return getResponseEntity(message, httpStatus, data);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus) {
        return getResponseEntity(message, httpStatus, null);
    }

    private static ResponseEntity<Object> getResponseEntity(String message, HttpStatus httpStatus, Object data) {
        Map<String, Object> responseBodyMap = getResponseMap(message, httpStatus);
        Map<String, Object> transformedResponseBodyMap = parseResponseBodyMap(httpStatus, data, responseBodyMap);
        return new ResponseEntity<>(transformedResponseBodyMap, httpStatus);
    }

    private static Map<String, Object> getResponseMap(String message, HttpStatus httpStatus) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", message);
        map.put("status", httpStatus.getReasonPhrase());
        map.put("code", httpStatus.value());
        return map;
    }

    private static Map<String, Object> parseResponseBodyMap(HttpStatus status, Object data, Map<String, Object> responseBodyMap) {
        if (data instanceof ArrayList && (status.is4xxClientError() || status.is5xxServerError())) {
            responseBodyMap.put("errors", data);
            return responseBodyMap;
        }

        if (ObjectUtils.isEmpty(data)) {
            data = new String[]{};
        }

        responseBodyMap.put("data", data);

        return responseBodyMap;
    }


}
