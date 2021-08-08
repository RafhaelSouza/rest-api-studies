package com.studies.foodorders.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ApiError {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime timestamp;
    private List<Object> objects;

    private String userMessage;

    @Getter
    @Builder
    public static class Object {

        private String name;
        private String userMessage;

    }

}
