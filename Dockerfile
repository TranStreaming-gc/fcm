# 1. Build Stage - Gradle 빌드 수행
FROM eclipse-temurin:21-jdk AS builder

# JAVA_HOME 환경 변수 설정
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 설정 파일과 Wrapper 복사 (의존성 캐싱)
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY ./gradlew /app/gradlew

# 4. JAVA_HOME 및 java 경로 확인 (디버깅)
RUN echo "JAVA_HOME: $JAVA_HOME" && \
    ls -al $JAVA_HOME && \
    which java && java -version

# 5. Gradle 의존성 캐시 활용 (의존성만 미리 다운로드)
RUN chmod +x ./gradlew && JAVA_HOME=$JAVA_HOME ./gradlew dependencies --no-daemon --parallel

# 6. 소스 코드 전체 복사
COPY . /app

# 7. 빌드 실행 (테스트 제외)
RUN ./gradlew clean build -x test --no-daemon --parallel

# 8. Runtime Stage - 최소한의 런타임 이미지 사용
FROM eclipse-temurin:21-jre-alpine

# 9. 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# 10. 런타임 포트 설정
EXPOSE 8080

# 11. 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
