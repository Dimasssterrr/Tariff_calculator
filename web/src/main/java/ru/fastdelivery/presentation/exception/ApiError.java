package ru.fastdelivery.presentation.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
@Slf4j
public record ApiError(
        @JsonIgnore
        HttpStatus httpStatus,
        String status,
        String message
) {
    public static ApiError badRequest(String message){
        log.error(HttpStatus.BAD_REQUEST + " error " + message);
        return new ApiError(HttpStatus.BAD_REQUEST, "error", message);
    }
}
