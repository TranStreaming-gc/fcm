package transtreaming.FCM.domain.fcm.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;
import transtreaming.FCM.domain.fcm.error.FcmError;
import transtreaming.FCM.domain.fcm.service.FcmService;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscribeListener implements MessageListener {
    private final RedisTemplate<String, Object> template;
    private final ObjectMapper objectMapper;
    private final FcmService fcmService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            log.info("channel name = {}", new String(pattern));
            String pubMessage = template.getStringSerializer().deserialize(message.getBody());
            log.info("get message = {}", pubMessage);
            MessageReqDto messageReqDto = objectMapper.readValue(pubMessage, MessageReqDto.class);
            fcmService.postFcm(messageReqDto);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(FcmError.JSON_FORMAT_ERROR.getMessage());
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
