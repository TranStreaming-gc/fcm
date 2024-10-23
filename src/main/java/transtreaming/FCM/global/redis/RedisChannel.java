package transtreaming.FCM.global.redis;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisChannel {
    FCM_CHANNEL("alert");

    private final String channelName;
}
