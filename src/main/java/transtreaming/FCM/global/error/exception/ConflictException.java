package transtreaming.FCM.global.error.exception;


import transtreaming.FCM.global.error.code.ErrorCode;
import transtreaming.FCM.global.error.code.GlobalErrorCode;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(GlobalErrorCode.CONFLICT);
    }

    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}


