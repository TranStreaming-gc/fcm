package transtreaming.FCM.global.redis;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import transtreaming.FCM.domain.fcm.dto.req.AlarmReqDto;
import transtreaming.FCM.domain.fcm.service.FcmService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisListener implements MessageListener {

    private final FcmService fcmService;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // Redis에서 수신한 메시지를 AlarmReqDto로 변환
            String body = new String(message.getBody());
            AlarmReqDto alarmReqDto = objectMapper.readValue(body, AlarmReqDto.class);

            // FCM 발송 처리
            fcmService.postFcm(alarmReqDto);
        } catch (FirebaseMessagingException | IOException e) {
            log.error("Failed to send FCM message from Redis: {}", e.getMessage());
        }
    }
}
