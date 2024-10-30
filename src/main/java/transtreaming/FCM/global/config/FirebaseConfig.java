package transtreaming.FCM.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void ini() {
        try {
            InputStream serviceAccount =
                    new ClassPathResource("k-pass-92025-firebase-adminsdk-ssn5d-d957d873bd.json")
                    .getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()  // Deprecated된 부분 수정
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
