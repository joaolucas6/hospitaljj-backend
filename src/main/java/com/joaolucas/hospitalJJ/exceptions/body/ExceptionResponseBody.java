package com.joaolucas.hospitalJJ.exceptions.body;

import java.time.LocalDateTime;

public record ExceptionResponseBody (String error, int errorCode, String message, LocalDateTime timestamp){
}
