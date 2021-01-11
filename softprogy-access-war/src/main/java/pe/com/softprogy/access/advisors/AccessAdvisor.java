
package pe.com.softprogy.access.advisors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pe.com.softprogy.access.commons.response.Status;
import pe.com.softprogy.access.enumeration.AccessCodeEnum;
import pe.com.softprogy.access.exception.AccessLogicException;

@ControllerAdvice
public class AccessAdvisor
{
    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler({ AccessLogicException.class })
    public ResponseEntity<Status> handlerKaceraLogicException(AccessLogicException ex)
    {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getCode().status(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Status> handlerException(Exception ex)
    {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(AccessCodeEnum.FAIL.status(), HttpStatus.BAD_REQUEST);
    }

}
