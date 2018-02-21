package igor.forum.config;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * Created by igorhara on 21/02/2018.
 */

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<VndErrors> notFoundException(final AuthenticationException e) {
        return error(e, HttpStatus.PRECONDITION_FAILED, e.getLocalizedMessage());
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<VndErrors> assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
}
