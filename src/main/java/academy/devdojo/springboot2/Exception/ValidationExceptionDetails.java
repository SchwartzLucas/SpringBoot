package academy.devdojo.springboot2.Exception;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails{
    private final String fields;
    private final String filedsMessage;

}
