# 1. Temurin JDK 21 이미지로 빌드 환경 설정
FROM eclipse-temurin:21-jdk AS builder

# 2. JAVA_HOME과 PATH 환경 변수 명시적 설정
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

# 3. 작업 디렉토리 설정
WORKDIR /app

# 4. Gradle 설정 파일과 Wrapper 복사 (캐시 활용)
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY ./gradlew /app/gradlew

# 5. Gradlew 실행 권한 부여 및 의존성 다운로드
RUN chmod +x /app/gradlew && \
    echo "Using JAVA_HOME: $JAVA_HOME" && \
    /app/gradlew dependencies --no-daemon

# 6. 나머지 소스 코드 복사 및 빌드 실행
COPY . /app
RUN ./gradlew clean build -x test --no-daemon

# 7. Temurin JDK 21 이미지로 런타임 설정
FROM eclipse-temurin:21-jdk

# 8. 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 9. 애플리케이션 포트 노출 및 실행
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
