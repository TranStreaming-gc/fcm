package transtreaming.FCM.domain.fcm.validate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import transtreaming.FCM.domain.fcm.error.FcmError;
import transtreaming.FCM.domain.fcm.repository.MemberRepository;
import transtreaming.FCM.global.error.exception.BusinessException;
import transtreaming.FCM.global.error.exception.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class FcmValidate {
    private final MemberRepository memberRepository;

    public void existsByToken(String token) {
        if (token == null) {
            throw new BusinessException(FcmError.TOKEN_NULL);
        }
        if(!memberRepository.existsByToken(token)) {
            throw new EntityNotFoundException(FcmError.MEMBER_NOT_FOUND);
        }
    }
}
