package learnStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Stream의 종류
 * 
 * -BaseStream  // 메소드들이 정의되어 있을 뿐 직접 사용되지는 않음
 *              // 아래 하위 스트림에서 사용
 *      - Stream
 *      - IntStream
 *      - LongStream
 *      - DoubleStream
 */

public class Hello01 {
    static int sum;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        // 1. 컬렉션에서 스트림 얻기
        List<String> strList = Arrays.asList("asdf", "qwer", "1234");
        Stream<String> strStream = strList.stream();
        strStream.forEach(s -> System.out.println(s));
        System.out.println();

        // 2. 배열로부터 스트림 얻기
        int[] intArr = { 1, 2, 3, 4, 3, 2, 1 };
        IntStream intStream = Arrays.stream(intArr);
        intStream.forEach((i) -> System.out.println(i));
        System.out.println();

        // 3. 숫자 범위로부터 스트림 얻기
        IntStream intStream1 = IntStream.rangeClosed(1, 100); // 100 포함
        IntStream intStream2 = IntStream.range(1, 100); // 100 미포함

        sum = 0; // 람다식에서 로컬 변수 갱신 못시킴
        intStream1.forEach(a -> sum += a);
        System.out.println(sum);

        sum = 0;
        intStream2.forEach(a -> sum += a);
        System.out.println(sum + "\n");

        // 4. 파일로부터 스트림 얻기
        String strPath = System.getProperty("user.dir") + File.separator + "src";
        strPath += File.separator + "learnStream" + File.separator + "애국가.txt";
        System.out.println(strPath);
        Path path = Paths.get(strPath); // Path 객체 생성

        // Files.lines() 메소드 사용
        Stream<String> stream = Files.lines(path, Charset.defaultCharset());
        stream.forEach(System.out::println);
        System.out.println();
        stream.close();

        // BufferedReader의 lines() 메소드 이용
        File file = path.toFile();
        // 파일리더와 버퍼 리더(추후 자세하게 학습)
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        stream = bufferReader.lines();
        stream.forEach(System.out::println);
        System.out.println();
        stream.close();
        bufferReader.close();
        fileReader.close();

        // 5. 디렉토리로부터 스트림 얻기
        Path dirPath = Paths.get("C:/");
        // Files.list() 메소드 이용
        Stream<Path> streamPath = Files.list(dirPath);
        streamPath.forEach(p -> System.out.println(p.getFileName()));
        streamPath.close();
    }

}
