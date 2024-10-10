package transtreaming.FCM.domain.fcm.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import transtreaming.FCM.domain.fcm.entity.Member;
import transtreaming.FCM.domain.fcm.error.FcmError;
import transtreaming.FCM.domain.fcm.repository.MemberRepository;
import transtreaming.FCM.global.error.exception.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class FcmHelper {
    private final MemberRepository memberRepository;

    public Member findByToken(String token) {
        return memberRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException(FcmError.MEMBER_NOT_FOUND));
    }
}
