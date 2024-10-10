package transtreaming.FCM.domain.fcm.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transtreaming.FCM.domain.fcm.dto.req.AlarmReqDto;
import transtreaming.FCM.domain.fcm.helper.FcmHelper;
import transtreaming.FCM.domain.fcm.validate.FcmValidate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FcmService {
    private final FcmHelper fcmHelper;
    private final FcmValidate fcmValidate;

    //token 받아서 처리
    public void postFcm(AlarmReqDto alarmReqDto) throws FirebaseMessagingException {
        alarmReqDto.token().forEach(fcmValidate::existsByToken);
        log.info("token = {}", alarmReqDto.token());
        // MulticastMessage를 생성하여 여러 토큰에 전송
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle("test")
                        .setBody("testMessage")
                        .build())
                .addAllTokens(alarmReqDto.token())
                .build();

        // 비동기 방식으로 메시지 전송
        BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message);

        // 각 응답 처리
        log.info("Successfully sent {} messages", response.getSuccessCount());
        log.error("Failed to send {} messages", response.getFailureCount());

        // 실패한 메시지 로그 출력
        response.getResponses().forEach(sendResponse -> {
            if (!sendResponse.isSuccessful()) {
                log.error("Failed to send message: {}", sendResponse.getException().getMessage());
            }
        });
    }
}
