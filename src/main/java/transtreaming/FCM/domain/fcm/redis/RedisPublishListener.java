package transtreaming.FCM.domain.fcm.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisPublishListener {
    private final RedisTemplate<String, Object> template;

    public void publish(ChannelTopic topic, MessageReqDto messageReqDto) {
        template.convertAndSend(topic.getTopic(), messageReqDto);
    }

    public void publish(ChannelTopic topic, String message) {
        template.convertAndSend(topic.getTopic(), message);
    }
}
