//package transtreaming.FCM.global.redis;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.firebase.messaging.FirebaseMessagingException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.stereotype.Service;
//import transtreaming.FCM.domain.fcm.dto.req.AlarmReqDto;
//import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;
//import transtreaming.FCM.domain.fcm.service.FcmService;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class RedisListener implements MessageListener {
//
//    private final FcmService fcmService;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        try {
//            // Redis에서 발행된 JSON 메시지 파싱 (제네릭 타입 명시)
//            String body = new String(message.getBody());
//            Map<String, Object> data = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {});
//
//            // FCM 토큰 및 GPS 데이터 추출
//            String token = (String) data.get("token");
//            Double latitude = (Double) data.get("latitude");
//            Double longitude = (Double) data.get("longitude");
//
//            // FCM 알림 처리
//            MessageReqDto alarmReqDto = null;
//            fcmService.postFcm(alarmReqDto);
//
//            // 로그 기록
//            log.info("Processed FCM token and GPS data: token={}, latitude={}, longitude={}", token, latitude, longitude);
//        } catch (FirebaseMessagingException | IOException e) {
//            log.error("Failed to send FCM message: {}", e.getMessage());
//        }
//    }
//}
