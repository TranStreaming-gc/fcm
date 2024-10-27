package transtreaming.FCM.domain.fcm.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;

import java.util.List;

import static transtreaming.FCM.domain.fcm.entity.QUsergps.usergps;

@Slf4j
@RequiredArgsConstructor
public class UsergpsQueryRepositoryImpl implements UsergpsQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TokenInfo> findByRegion(String region) {
        String[] addresses = region.split(",");
        String pronounce = addresses[0].split(" ")[0];
        BooleanBuilder builder = builder(addresses, pronounce);
        for (String address : addresses) {
            address = address.trim();
            if(address.startsWith(region)) {
                builder.or(usergps.address.like(address + "%"));
            }
        }
        return queryFactory.select(
                Projections.constructor(TokenInfo.class,
                        usergps.deviceToken
                ))
                .from(usergps)
                .where(builder)
                .fetch();
    }

    private BooleanBuilder builder(String[] addresses, String pronounce) {
        BooleanBuilder builder = new BooleanBuilder();
        for (String address : addresses) {
            address = address.trim();
            if(address.startsWith(pronounce)) {
                builder.or(usergps.address.like(address + "%"));
            }
        }
        return builder;
    }
}
