package transtreaming.FCM.domain.fcm.dto.req;

public record MessageReqDto(
        String title,
        String message,
        String region
) {
}
