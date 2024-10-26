package transtreaming.FCM.domain.fcm.repository;

import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;

import java.util.List;

public interface UsergpsQueryRepository {

    List<TokenInfo> findByRegion(String region);
}
