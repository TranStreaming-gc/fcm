package transtreaming.FCM.domain.fcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transtreaming.FCM.domain.fcm.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByToken(String token);

    boolean existsByToken(String token);
}
