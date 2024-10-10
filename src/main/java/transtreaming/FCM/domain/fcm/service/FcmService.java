package transtreaming.FCM.domain.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transtreaming.FCM.domain.fcm.dto.req.AlarmReqDto;
import transtreaming.FCM.domain.fcm.helper.FcmHelper;
import transtreaming.FCM.domain.fcm.validate.FcmValidate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FcmService {
    private final FcmHelper fcmHelper;
    private final FcmValidate fcmValidate;

    //token 받아서 처리
    public void postFcm(AlarmReqDto alarmReqDto) throws FirebaseMessagingException {
        fcmValidate.existsByToken(alarmReqDto.token());
        log.info("token = {}", alarmReqDto.token());
        String send = FirebaseMessaging.getInstance().send(Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("test")
                        .setBody("testMessage")
                        .build())
                .setToken(alarmReqDto.token())
                .build());
        log.info("message sent: {}", send);
    }
}
