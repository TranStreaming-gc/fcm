package transtreaming.FCM.domain.fcm.dto.req;

import java.util.List;

public record AlarmReqDto(
        List<String> token
) {
}
