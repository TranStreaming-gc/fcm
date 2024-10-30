# 1. 기본 이미지로 Java 21 사용
FROM eclipse-temurin:21-jdk AS builder
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 설정 파일과 Wrapper 복사 (의존성 캐시 활용)
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY ./gradlew /app/gradlew

# 4. Gradlew에 실행 권한 부여 및 의존성 다운로드
RUN echo "Using JAVA_HOME: $JAVA_HOME" && \
    chmod +x /app/gradlew && /bin/bash ./gradlew dependencies --no-daemon

# 5. 나머지 소스 코드 복사
COPY . /app

# 6. 프로젝트 빌드 (테스트 제외)
RUN ./gradlew clean build -x test --no-daemon

# 런타임 이미지 설정
FROM eclipse-temurin:21-jdk

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 7. 애플리케이션 포트 노출
EXPOSE 8080

# 8. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
