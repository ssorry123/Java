package learn_NIO_Network;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/* IO
 * 스트림 방식
 * 버퍼 없음
 * 비동기방식 지원 안함
 * 블로킹 방식만 지원
 */

/*
 * NIO
 * 채널 방식
 * 버퍼 있음
 * 비동기 방식 지원
 * 블로킹, 논블로킹(인터럽트 방식) 모두 지원
 */
/*
 * NIO는 연결 클라이언트 수가 많고, 하나의 입출력 처리작업이 오래 걸리지 않는 경우에 좋다
 * 
 * 대용량 데이터를 처리할 경우 IO가 더 유리하다
 * NIO는 버퍼 할당 크기도 문제가 되고, 모든 입출력 작업에 버퍼를 무조건 사용해야 하므로
 * 받은 즉시 처리하는 IO보다는 더 복잡하다.
 * 
 */

public class Hello00 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) throws IOException {
        // 1. 상대 경로를 이용해서 소스파일에 대한 Path객체를 얻고
        // 파일명, 부모 디렉토리명, 중첩 경로 수, 경로상에 있는 모든 디렉토리를 출력한다.
        String filePath = "src/learn_NIO_Network/Hello00.java";
        Path path = Paths.get(filePath);
        p("파일명: " + path.getFileName());
        p("부모디렉토리 명: " + path.getParent().getFileName());
        p("중첩 경로 수: " + path.getNameCount()); // 3
        p("");

        // "/"으로 split
        for (int i = 0; i < path.getNameCount(); ++i) {
            p(path.getName(i));
        }
        p("");

        // "/"으로 split
        Iterator<Path> iter = path.iterator();
        while (iter.hasNext()) {
            Path temp = iter.next();
            p(temp.getFileName());
        }
        p("");

        // 2. 파일 시스템 정보
        // FileSystem 인터페이스
        FileSystem fileSystem = FileSystems.getDefault();

        // 드라이버 정보를 가진 FileStore 객체들 리턴
        for (FileStore store : fileSystem.getFileStores()) {
            p("드라이버 이름: " + store.name());
            p("파일시스템: " + store.type());
            p("전체 공간: " + store.getTotalSpace());
            p("사용중인 공간: " + (store.getTotalSpace() - store.getUnallocatedSpace()) + "바이트");
            p("사용가능한 공간: " + store.getUsableSpace() + "바이트");
            p("");
        }

        p("파일 구분자 : " + fileSystem.getSeparator());
        p("");

        // 루트 디렉토리 정보를 가진 Path 객체 리턴
        // (ex C:\, D:\)
        for (Path p : fileSystem.getRootDirectories()) {
            p(p.toString());
        }

        // 3. 파일 속성 읽기 및 파일, 디렉토리 생성/삭제
        // java.nio.file.Files
        p("디렉토리 여부: " + Files.isDirectory(path));
        p("파일 여부: " + Files.isRegularFile(path));
        p("마지막 수정 시간: " + Files.getLastModifiedTime(path));
        p("파일 크기: " + Files.size(path));
        p("소유자: " + Files.getOwner(path).getName());
        p("숨김 파일 여부: " + Files.isHidden(path));
        p("읽기 가능 여부: " + Files.isReadable(path));
        p("쓰기 가능 여부: " + Files.isWritable(path));
        p("");
        // ... 등등

        // 4. 디렉토리 내용 얻기
        Path pathNewDir = Paths.get("src/learn_NIO_Network/Temp");
        Path pathNewFile = Paths.get("src/learn_NIO_Network/Temp/test.txt");

        // 경로상에 존재하지 않는 모든 디렉토리 생성
        if (Files.notExists(pathNewDir))
            Files.createDirectories(pathNewDir);
        // 파일 생성
        if (Files.notExists(pathNewFile))
            Files.createFile(pathNewFile);

        // 디렉토리 내용 스트림
        Path dirPath = Paths.get("src/learn_NIO_Network");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath);
        for (Path p : directoryStream) {
            if (Files.isDirectory(p))
                p("[디렉토리]: " + p.getFileName());
            else
                p("[파일]: " + p.getFileName() + " (크기: " + Files.size(p) + ")");

        }
    }

}
