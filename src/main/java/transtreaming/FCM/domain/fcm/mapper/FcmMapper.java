package transtreaming.FCM.domain.fcm.mapper;

import org.springframework.stereotype.Component;
import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;
import transtreaming.FCM.domain.fcm.dto.res.MessageResDto;

@Component
public class FcmMapper {
    public MessageResDto toMessageResDto(MessageReqDto reqDto, int successMessage, int failMessage) {
        return MessageResDto.of(reqDto, successMessage, failMessage);
    }
}
