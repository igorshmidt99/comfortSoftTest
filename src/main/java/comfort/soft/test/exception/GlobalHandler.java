package comfort.soft.test.exception;

import comfort.soft.test.parser.NoSuchXlsx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(exception = NoSuchXlsx.class)
    public ResponseEntity<String> handleFsException(NoSuchXlsx ex) {
        String message = ex.getMessage();
        String errorMessage = String.format("No such file by passed path. Additional info: %s", message);
        log.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(404));
    }

}
