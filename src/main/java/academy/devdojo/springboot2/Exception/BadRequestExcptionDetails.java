package academy.devdojo.springboot2.Exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class BadRequestExcptionDetails extends  ExceptionDetails{

}
