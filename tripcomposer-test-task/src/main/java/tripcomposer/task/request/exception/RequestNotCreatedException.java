package tripcomposer.task.request.exception;

import javax.ejb.ApplicationException;

/**
 * Created by vika on 23.10.15.
 */
@ApplicationException(rollback = false)
public class RequestNotCreatedException extends RuntimeException {
    public RequestNotCreatedException() {
        super();
    }

    public RequestNotCreatedException(Throwable cause) {
        super(cause);
    }

    public RequestNotCreatedException(String message) {
        super(message);
    }

    public RequestNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception getCausedByException() {
        return (Exception) getCause();
    }
}
