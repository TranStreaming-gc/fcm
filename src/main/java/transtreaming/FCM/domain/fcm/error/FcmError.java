package transtreaming.FCM.domain.fcm.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import transtreaming.FCM.global.error.code.ErrorCode;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum FcmError implements ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member Not Found");

    private final HttpStatus httpStatus;
    private final String message;
}
