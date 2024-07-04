package raingor.ru.catalogueservice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class BadRequestControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException ex, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                this.messageSource.getMessage("errors.400.title", new Object[0],
                        "errors.400.title", locale)
        );
        problemDetail.setProperty("errors", ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}
