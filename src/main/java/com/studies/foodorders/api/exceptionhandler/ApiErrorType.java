package com.studies.foodorders.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {

    INTERNAL_SERVER_ERROR("Internal Server Error","/internal-server-error"),
    INVALID_PARAMETER("Invalid Parameter","/invalid-parameter"),
    INVALID_REQUEST("Invalid Request","/invalid-request"),
    BUSINESS_ERROR("Business Error", "/business-error"),
    RESOURCE_NOT_FOUND("Resource Not Found", "/resource-not-found"),
    INCOMPREHENSIBLE_MESSAGE("Incomprehensible Message", "/incomprehensible-message"),
    ACCESS_DENIED("Access denied", "/access-denied"),
    USED_ENTITY("Used Entity", "used-entity");

    private String title;
    private String uri;

    ApiErrorType(String title, String path) {
        this.title = title;
        this.uri = "http://localhost:8080/foodorders" + path;
    }
}
