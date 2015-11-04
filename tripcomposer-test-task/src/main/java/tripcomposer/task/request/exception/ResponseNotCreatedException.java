package tripcomposer.task.request.exception;

import javax.ejb.ApplicationException;

/**
 * Created by vika on 23.10.15.
 */
@ApplicationException(rollback = false)
public class ResponseNotCreatedException extends RuntimeException {
    public ResponseNotCreatedException() {
        super();
    }

    public ResponseNotCreatedException(Throwable cause) {
        super(cause);
    }

    public ResponseNotCreatedException(String message) {
        super(message);
    }

    public ResponseNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception getCausedByException() {
        return (Exception) getCause();
    }
}
