package transtreaming.FCM.domain.fcm.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transtreaming.FCM.domain.fcm.dto.info.TokenInfo;
import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;
import transtreaming.FCM.domain.fcm.helper.FcmHelper;
import transtreaming.FCM.domain.fcm.validate.FcmValidate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FcmService {
    private final FcmHelper fcmHelper;
    private final FcmValidate fcmValidate;

    // token 받아서 처리
    public void postFcm(MessageReqDto messageReqDto) throws FirebaseMessagingException {
        log.info("region = {}", messageReqDto.region());
        List<TokenInfo> tokenInfos = fcmHelper.findByRegion(messageReqDto.region());
        List<String> tokens = tokenInfos.stream()
                .map(TokenInfo::token) // TokenInfo의 token 필드를 추출
                .toList();
        log.info("tokens = {}", tokens);

        // MulticastMessage를 생성하여 여러 토큰에 전송
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(messageReqDto.title())
                        .setBody(messageReqDto.message())
                        .build())
                .addAllTokens(tokens)
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
