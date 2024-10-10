package transtreaming.FCM.domain.fcm.api;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transtreaming.FCM.domain.fcm.dto.req.AlarmReqDto;
import transtreaming.FCM.domain.fcm.service.FcmService;
import transtreaming.FCM.global.domain.SuccessResponse;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmController {
    private final FcmService fcmService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> postFcm(@RequestBody AlarmReqDto reqDto) throws FirebaseMessagingException {
        fcmService.postFcm(reqDto);
        return SuccessResponse.ok(null);
    }
}
