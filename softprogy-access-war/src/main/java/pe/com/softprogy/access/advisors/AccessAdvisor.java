
package pe.com.softprogy.access.advisors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.enumeration.AccessCodeEnum;
import pe.com.softprogy.access.exception.AccessLogicException;

@ControllerAdvice
public class AccessAdvisor
{
    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler({ AccessLogicException.class })
    public ResponseEntity<Response<?>> handlerKaceraLogicException(AccessLogicException ex)
    {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<Response<?>>(ex.getCode().createResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Response<?>> handlerException(Exception ex)
    {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<Response<?>>(AccessCodeEnum.FAIL.createResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ TransactionSystemException.class })
    public ResponseEntity<Response<?>> handlerException(TransactionSystemException ex)
    {
        logger.error(ex.getMessage(), ex);
        if (ex.getOriginalException() instanceof AccessLogicException)
        {
            return handlerKaceraLogicException((AccessLogicException) ex.getOriginalException());
        }
        else
        {
            return handlerException(ex);
        }
    }

}
