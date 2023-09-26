package com.studies.foodorders.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("ApiError")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ApiError {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "2023-09-15T13:24:02.70844Z", value = "UTC timestamp" , position = 5)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "http://localhost:8080/invalid-parameter", position = 10)
    private String type;

    @ApiModelProperty(example = "Invalid Parameter", position = 15)
    private String title;

    @ApiModelProperty(example = "Invalid Fields", position = 20)
    private String detail;

    @ApiModelProperty(example = "Invalid Fields", position = 25)
    private String userMessage;

    @ApiModelProperty(value = "List of objects or fields that generated the error (Optional)", position = 30)
    private List<Object> objects;

    @ApiModel("IssueObject")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "price")
        private String name;

        @ApiModelProperty(example = "Price is required")
        private String userMessage;

    }

}
