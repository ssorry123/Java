package learn1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

// Map 컬렉션

public class Hello34 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO Auto-generated method stub
        // 1. HashMap
        // 해쉬맵의 키로 사용할 객체는
        // hashCode와 equals 메소드를 재정의해서
        // 동등객체가 될 조건을 정해야 한다.

        // 객체를 하나씩 처리
        Map<String, Integer> map1 = new HashMap<>();
        String[] key = new String[] { "가나다", "apple", "홍길동" };
        for (int i = 0; i < key.length; ++i) {
            map1.put(key[i], i);
        }

        // 키만 가져오기
        Set<String> keySet = map1.keySet();
        for (String k : keySet) {
            System.out.println(k + " " + map1.get(k));
        }

        // 키와 값을 가져오기
        Set<Map.Entry<String, Integer>> entrySet = map1.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            String k = entry.getKey();
            Integer val = entry.getValue();
            System.out.println(k + " " + val);
        }

        // 2. HashTable
        // HashMap과 같음
        // 다른점 : Thread Safe

        /*
         * Properties HashTable의 하위클래스, 키와 타입을 String타입으로 제한 주로 .properties 파일을 읽을 때 주로
         * 사용
         * 
         * 프로퍼티 파일 : 키와 값이 = 기호로 연결되어 있는 텍스트 파일 ISO 8859-1 문자셋으로 저장됨 한글과 같이 표현할 수 없는 경우
         * 유니코드로 변환되어 저장됨.
         */
        // 프로퍼티 객체 생성
        Properties properties = new Properties();
        // 파일의 경로 가져오기
        // getResource : 주어진 파일의 상대경로를 URL 객체로 리턴
        // getPath : 파일의 절대경로를 리턴
        String path = Hello34.class.getResource("database.properties").getPath();
        path = URLDecoder.decode(path, "utf-8"); // utf-8로 인코딩
        // 파일경로를 파일리더로 읽어서 프로퍼티에 로드
        properties.load(new FileReader(path));

        // 프로퍼티도 매빙므로 get으로 가져올 수 있지만, get은 Object타입을 리턴함
        // String으로 강제 타입 변환해야함
        String driver = (String) properties.get("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        System.out.println("driver : " + driver);
        System.out.println("url : " + url);
        System.out.println("username : " + username);
        System.out.println("password : " + password);

    }

}
