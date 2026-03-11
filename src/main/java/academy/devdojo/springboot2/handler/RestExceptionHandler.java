package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.Exception.BadRequestException;
import academy.devdojo.springboot2.Exception.BadRequestExcptionDetails;
import academy.devdojo.springboot2.Exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExcptionDetails> handlerBadRequestExcption(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExcptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad request Exception, check the documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerValidationException(
            MethodArgumentNotValidException exception){
        log.info("fields {}", exception.getBindingResult().getFieldError().getField());
        List<FieldError> fildErrors = exception.getBindingResult().getFieldErrors();
        String fields = fildErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fildErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",  "));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad request Exception, invalid fields")
                        .details("check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .filedsMessage(fieldMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
