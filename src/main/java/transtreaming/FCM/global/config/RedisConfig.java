package transtreaming.FCM.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import transtreaming.FCM.global.redis.RedisListener;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}") // Redis 호스트 주소를 불러옴
    private String redisHost;

    @Value("${spring.data.redis.port}") // Redis 포트 번호를 불러옴
    private int redisPort;

    @Value("${spring.data.redis.channel}") // application.yml에서 채널 값을 불러옴
    private String redisChannel;

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort); // Redis 서버와의 연결 설정
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(redisChannel)); // 설정된 채널 이름을 사용
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RedisListener redisListener) {
        return new MessageListenerAdapter(redisListener);
    }
}
