/*
 * 별도 세팅 없이 다운받은 javafx 라이브러리 사용하려면 module-info.java 필수
 * 
 * 1. 우선 다운받은 SDK폴더의 lib 폴더의 jar 파일들 추가해야함
 * (이클립스 추가 방법)
 * [프로젝트] -> [build path] -> [add external archive]
 * -> lib폴더의 jar 파일들 추가
 * 
 * 2. 사용할 모듈 module-info.java에 정의
 * 
 */

module learnJavaFX {
    exports application;
    exports application01;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
}