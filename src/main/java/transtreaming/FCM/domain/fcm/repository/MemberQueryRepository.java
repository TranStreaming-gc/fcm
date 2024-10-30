package transtreaming.FCM.domain.fcm.repository;

import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;

import java.util.List;

public interface MemberQueryRepository {

    List<TokenInfo> findByRegion(String region);
}
