# 1. Temurin JDK 21 이미지로 빌드
FROM eclipse-temurin:21-jdk AS builder

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 설정 파일과 Wrapper 복사
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY ./gradlew /app/gradlew

# 4. 권한 부여 및 의존성 다운로드
RUN chmod +x /app/gradlew && ./gradlew dependencies --no-daemon

# 5. 소스 코드 복사 및 빌드 실행
COPY . /app
RUN ./gradlew clean build -x test --no-daemon

# 6. Temurin JDK 21 이미지로 런타임 설정
FROM eclipse-temurin:21-jdk

# 7. 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 8. 포트 노출 및 애플리케이션 실행
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
