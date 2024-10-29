# 1. 기본 이미지로 Java 21 사용
FROM eclipse-temurin:21-jdk AS builder

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 설정 파일과 Gradle Wrapper 복사 (의존성 캐싱 목적)
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY ./gradlew /app/gradlew

# 4. Gradle 의존성 미리 다운로드 (의존성 캐시를 활용)
RUN chmod +x ./gradlew && ./gradlew dependencies --no-daemon

# 5. 나머지 소스 코드 복사
COPY . /app

# 6. 프로젝트 빌드
RUN chmod +x ./gradlew && ./gradlew clean build -x test --no-daemon

# 런타임 이미지 설정
FROM eclipse-temurin:21-jdk

# 빌드에서 생성된 JAR 파일을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 7. 애플리케이션 포트 설정
EXPOSE 8080

# 8. 실행 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]
