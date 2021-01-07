package learn_NIO_Network;

import java.nio.ByteBuffer;

/*
 * 버퍼
 * NIO에서는 데이터를 입출력하기 위해 항상 버퍼를 사용해야 한다.
 * 
 * 1. 데이터 타입에 따른 버퍼
 * Buffer
 *      ByteBuffer
 *          MappedByteBuffer
 *      CharBuffer
 *      ShortBuffer
 *      IntBuffer
 *      LongBuffer
 *      FloatBuffer
 *      DoubleBuffer
 * 
 * 2. 메모리 위치에 따른 버퍼
 * 2-1. non-direct 버퍼
 * JVM의 힙 메모리
 * 버퍼 생성이 빠름
 * 버퍼의 크기 작음
 * 입출력 성능 낮음
 * 
 * 2-2. direct 버퍼
 * 운영체제의 메모리
 * 버퍼 생성이 느림
 * 버퍼의 크기 큼 (큰 데이터일때 유리)
 * 입출력 성능 (입출력이 빈번할때 유리)
 */

public class Hello02 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // 1. 버퍼 크기 한계 비교
        int mega = 0;
        try {
            while (true) {
                ByteBuffer directBuffer = ByteBuffer.allocateDirect(mega * 1024 * 1024);
                mega += 100;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); // capacity < 0: (-2092957696 < 0)
            System.out.println("다이렉트: " + mega + "mb 한계"); // 2100mb
        }
        System.out.println();

        mega = 0;
        try {
            while (true) {
                ByteBuffer nonDirectBuffer = ByteBuffer.allocate(mega * 1024 * 1024);
                mega += 100;
            }
        } catch (OutOfMemoryError e) {
            System.out.println(e.getMessage()); // Java heap space
            System.out.println("넌다이렉트: " + mega + "mb 한계"); // 1000mb
        }
        System.out.println();
        
    }

}
