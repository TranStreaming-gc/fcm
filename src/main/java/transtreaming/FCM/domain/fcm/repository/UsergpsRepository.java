package transtreaming.FCM.domain.fcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transtreaming.FCM.domain.fcm.entity.Usergps;

import java.util.Optional;

public interface UsergpsRepository extends JpaRepository<Usergps, Long>, UsergpsQueryRepository {

    Optional<Usergps> findByDeviceToken(String token);

    boolean existsByDeviceToken(String token);
}
