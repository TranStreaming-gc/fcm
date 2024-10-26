package transtreaming.FCM.domain.fcm.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;

import java.util.List;

import static transtreaming.FCM.domain.fcm.entity.QUsergps.usergps;


@RequiredArgsConstructor
public class UsergpsQueryRepositoryImpl implements UsergpsQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TokenInfo> findByRegion(String region) {
        return queryFactory.select(
                Projections.constructor(TokenInfo.class,
                        usergps.deviceToken
                ))
                .from(usergps)
                .where(usergps.address.like(region + "%"))
                .fetch();
    }
}
