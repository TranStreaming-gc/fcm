package transtreaming.FCM.domain.fcm.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import transtreaming.FCM.domain.fcm.dto.req.MessageReqDto;

/**
 * 보낸 메시지에 대한 응답값
 * 해당 알림의 날짜 + 타이틀 + 내용 + 지역 + 보낸 메시지 수
 */
@Builder(access = AccessLevel.PRIVATE)
public record MessageResDto(
        String CRT_DT,
        String CRT_TITLE,
        String MSG_CN,
        String RCPTN_RGN_NM,
        int SUCCESS_MSG_COUNT,
        int FAIL_MSG_COUNT
) {
    public static MessageResDto of(MessageReqDto reqDto, int successMessage, int failMessage) {
        return MessageResDto
                .builder()
                .CRT_DT(reqDto.CRT_DT())
                .CRT_TITLE(reqDto.CRT_TITLE())
                .MSG_CN(reqDto.MSG_CN())
                .RCPTN_RGN_NM(reqDto.RCPTN_RGN_NM())
                .SUCCESS_MSG_COUNT(successMessage)
                .FAIL_MSG_COUNT(failMessage)
                .build();
    }
}
