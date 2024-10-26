package transtreaming.FCM.domain.fcm.dto.req;

public record MessageReqDto(
        String CRT_DT,
        String DST_SE_NM,
        String MSG_CN,
        String RCPTN_RGN_NM
) {
}
