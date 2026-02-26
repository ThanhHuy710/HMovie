package com.example.HMovie.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    EMAIL_EXISTED(1008, "Email existed, please choose another one", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1009, "Username existed, please choose another one", HttpStatus.BAD_REQUEST),
    USERNAME_IS_MISSING(1010, "Please enter username", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1011, "User not existed", HttpStatus.BAD_REQUEST),
    
    MOVIE_NOT_FOUND(1012, "Movie not found", HttpStatus.NOT_FOUND),
    PLAN_NOT_FOUND(1013, "Plan not found", HttpStatus.NOT_FOUND),
    GENRE_NOT_FOUND(1014, "Genre not found", HttpStatus.NOT_FOUND),
    EPISODE_NOT_FOUND(1015, "Episode not found", HttpStatus.NOT_FOUND),
    FAVORITE_NOT_FOUND(1016, "Favorite not found", HttpStatus.NOT_FOUND),
    FEEDBACK_NOT_FOUND(1017, "Feedback not found", HttpStatus.NOT_FOUND),
    MOVIE_GENRE_NOT_FOUND(1018, "Movie Genre relation not found", HttpStatus.NOT_FOUND),
    INVOICE_NOT_FOUND(1019, "Invoice not found", HttpStatus.NOT_FOUND),
    VIEW_NOT_FOUND(1020, "View not found", HttpStatus.NOT_FOUND),
    CART_NOT_FOUND(1021, "Cart item not found", HttpStatus.NOT_FOUND),
    PAGE_NOT_FOUND(1022, "Page not found", HttpStatus.NOT_FOUND),
    PROFILE_NOT_FOUND(1022, "profile not found", HttpStatus.NOT_FOUND),
    RATING_NOT_FOUND(1021,"rating of feedback must be not empty" ,HttpStatus.BAD_REQUEST ),
    USERNAME_OR_PASSWORD_INCORECT(1022,"please,check your username or password" ,HttpStatus.BAD_REQUEST );

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final HttpStatusCode statusCode;
    private final String message;
}
