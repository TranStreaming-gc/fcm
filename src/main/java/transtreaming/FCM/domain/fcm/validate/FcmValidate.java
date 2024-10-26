package transtreaming.FCM.domain.fcm.validate;

import org.springframework.stereotype.Component;
import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;
import transtreaming.FCM.domain.fcm.error.FcmError;
import transtreaming.FCM.global.error.exception.BusinessException;

import java.util.List;

@Component
public class FcmValidate {

    public void existsByToken(List<TokenInfo> token) {
        if(token.isEmpty()) {
            throw new BusinessException(FcmError.NO_TARGET_USER);
        }
    }
}
