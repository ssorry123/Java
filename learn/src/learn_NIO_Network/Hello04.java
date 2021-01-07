package learn_NIO_Network;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Hello04 {

    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // 1. Buffer의 위치 속성
        // 0 <= mark <= position <= limit <= capacity
        // position : 현재 읽거나 쓰는 위치
        // limit : 버퍼에서 읽거나 쓸 수 있는 위치의 한계
        // mark : reset() 메소드를 실행했을 때에 돌아오는 위치를 지정하는 인덱스
        // mark() 메소드로 지정할 수 있음.
        p("7바이트 크기 버퍼 생성");
        ByteBuffer buf = ByteBuffer.allocateDirect(7);
        printState(buf); // 0, 7, 7

        // 상대적 저장
        buf.put((byte) 10);
        buf.put((byte) 7);
        p("2바이트 저장 후");
        printState(buf); // 2, 7, 7

        buf.put((byte) 3);
        buf.put((byte) 4);
        buf.put((byte) 5);
        p("3바이트 저장 후");
        printState(buf); // 5, 7, 7

        buf.flip(); // 버퍼에 데이터를 읽기 위해 위치 속성값 변경
        p("flip() 실행 후");
        printState(buf); // 0, 5, 7

        buf.get(new byte[3]);
        p("3바이트 읽은 후");
        printState(buf); // 3, 5, 7

        buf.mark();
        p("현재 pos위치로 mark");

        buf.get(new byte[2]);
        p("2바이트 읽은 후");
        printState(buf); // 5, 5, 7

        buf.reset(); // 마크된 위치가 없으면 예외
        p("pos를 mark 위치로 옮김");
        printState(buf); // 3, 5, 7

        buf.rewind(); // limit는 변하지 않음, mark는 사라짐, pos 0으로
        p("rewind 실행 후");
        printState(buf); // 0, 5, 7

        buf.clear(); // 모든 위치정보 초기화
        p("clear 실행 후");
        printState(buf); // 0, 0, 7

        printItem(buf);
        buf.put((byte) -1);
        buf.put((byte) -2);
        p("2바이트 저장 후");
        printState(buf);
        printItem(buf);

        buf.limit(buf.position() + 3);
        p("limit 변경");
        printState(buf);

        // pos~limit 데이터를 0위치로 이동
        // limit는 capacity로 이동
        // pos는 3으로 이동
        // 버퍼에 데이터는 남아있음.
        buf.compact();
        p("compact 실행 후");
        printState(buf);
        printItem(buf);
    }

    public static void printState(Buffer buf) {
        System.out.print("\tposition:" + buf.position() + ",");
        System.out.print("\tlimit:" + buf.limit() + ",");
        p("\tcapacity:" + buf.capacity());
    }

    public static void printItem(ByteBuffer buf) {
        int size = buf.capacity();

        for (int i = 0; i < size; ++i) {
            System.out.print(buf.get(i) + ", ");
        }
        p("");
    }
}
