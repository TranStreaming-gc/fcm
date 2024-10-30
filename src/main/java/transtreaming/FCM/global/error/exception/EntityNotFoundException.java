package transtreaming.FCM.global.error.exception;


import transtreaming.FCM.global.error.code.ErrorCode;
import transtreaming.FCM.global.error.code.GlobalErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(GlobalErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

