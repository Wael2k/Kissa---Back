package PFA.project.config.exceptions;

import PFA.project.config.exceptions.utils.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.List;

public class BadRequestException extends BaseException {
    public BadRequestException(String code, String message, Errors errors) {
        super(HttpStatus.BAD_REQUEST, code,message,errors);
    }
    public BadRequestException(String code, String message, List<String> errors) {
        super(HttpStatus.BAD_REQUEST, code,message,errors);
    }
    public BadRequestException(String code, String message) {
        super(HttpStatus.BAD_REQUEST, code,message );
    }
    public BadRequestException( String message, Errors errors) {
        super(HttpStatus.BAD_REQUEST, message, errors);
    }
}
