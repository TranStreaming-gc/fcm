package transtreaming.FCM.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import transtreaming.FCM.domain.fcm.redis.RedisSubscribeListener;

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
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return redisTemplate;
    }

    @Bean
    RedisMessageListenerContainer container(RedisSubscribeListener redisSubscribeListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(redisSubscribeListener, new PatternTopic(redisChannel));
        return container;
    }

//    @Bean
//    MessageListenerAdapter listenerAdapter(RedisListener redisListener) {
//        return new MessageListenerAdapter(redisListener);
//    }
}
