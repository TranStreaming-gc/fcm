package transtreaming.FCM.domain.fcm.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;
import transtreaming.FCM.domain.fcm.redis.RedisPublishListener;
import transtreaming.FCM.domain.fcm.service.FcmService;
import transtreaming.FCM.global.domain.SuccessResponse;
import transtreaming.FCM.global.redis.RedisChannel;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmController {
    private final FcmService fcmService;
    private final RedisPublishListener redisPublishListener;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> postFcm(@RequestBody MessageReqDto reqDto) {
        redisPublishListener.publish(new ChannelTopic(RedisChannel.FCM_CHANNEL.getChannelName()), reqDto);
        return SuccessResponse.ok(null);
    }
}
