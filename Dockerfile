# Temurin JDK 21 이미지 사용
FROM eclipse-temurin:21-jdk

# JAVA_HOME과 PATH 설정
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드와 Gradle 파일 복사
COPY . /app

# Gradle Wrapper 권한 부여 및 빌드 수행
RUN chmod +x /app/gradlew && \
    /app/gradlew clean build -x test --no-daemon

# 포트 노출 및 애플리케이션 실행
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/*.jar"]
