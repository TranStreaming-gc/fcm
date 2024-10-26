package transtreaming.FCM.domain.fcm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Usergps {
    @Id
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "device_time", nullable = false)
    private String deviceTime;

    @Column(name = "lat", nullable = false)
    private String lat;

    @Column(name = "lng", nullable = false)
    private String lng;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    @Column(name = "address", nullable = false)
    private String address;
}
