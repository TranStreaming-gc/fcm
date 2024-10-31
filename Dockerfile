# 1. Build Stage - Gradle 빌드 수행
FROM eclipse-temurin:21-jdk AS builder

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 설정 파일과 Wrapper 복사 (의존성 캐싱)
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY ./gradlew /app/gradlew

# 4. Gradle 의존성 캐시 활용 (의존성만 미리 다운로드)
RUN chmod +x ./gradlew && ./gradlew dependencies --no-daemon --parallel

# 5. 소스 코드 전체 복사
COPY . /app

# 6. 빌드 실행 (테스트 제외)
RUN ./gradlew clean build -x test --no-daemon --parallel

# 7. Runtime Stage - 최소한의 런타임 이미지 사용
FROM eclipse-temurin:21-jre-alpine

# 8. 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# 9. 런타임 포트 설정
EXPOSE 8080

# 10. 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
