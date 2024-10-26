package transtreaming.FCM.domain.fcm.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;
import transtreaming.FCM.domain.fcm.entity.Usergps;
import transtreaming.FCM.domain.fcm.error.FcmError;
import transtreaming.FCM.domain.fcm.repository.UsergpsRepository;
import transtreaming.FCM.global.error.exception.EntityNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FcmHelper {
    private final UsergpsRepository usergpsRepository;

    public Usergps findByToken(String token) {
        return usergpsRepository.findByDeviceToken(token)
                .orElseThrow(() -> new EntityNotFoundException(FcmError.MEMBER_NOT_FOUND));
    }

    public List<TokenInfo> findByRegion(String region) {
        return usergpsRepository.findByRegion(region);
    }
}
