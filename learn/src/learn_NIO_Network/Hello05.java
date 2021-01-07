package learn_NIO_Network;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/*
 * 버퍼 변환
 * 
 * 채널이 데이터를 읽고 쓰는 버퍼는 모두 ByteBuffer다.
 * 채널을 통해 읽은 데이터를 복원하려면 ByteBuffer를 다른 타입 버퍼로 변환해야 한다.
 * 반대로 문자열 등 다른 타입 버퍼의 내용을 채널을 통해 쓰고 싶다면 ByteBuffer로 변환해야 한다.
 */

public class Hello05 {

    public static void main(String[] args) {
        // 1. ByteBuffer <-> String
        Charset charset = Charset.forName("UTF-8");
        // 문자열 -> 인코딩 -> ByteBuffer
        String data = "안녕방가방가";
        ByteBuffer byteBuffer = charset.encode(data);
        // ByteBuffer -> 디코딩 -> CharBuffer -> 문자열
        data = charset.decode(byteBuffer).toString();
        System.out.println(data);

        // 2. ByteBuffer <-> IntBuffer
        // int[] -> IntBuffer -> ByteBuffer
        int[] intArr = { 33, 11 };
        IntBuffer writeIntBuffer = IntBuffer.wrap(intArr);
        ByteBuffer writeByteBuffer = ByteBuffer.allocate(writeIntBuffer.capacity() * 4);
        for (int i = 0; i < writeIntBuffer.capacity(); ++i) {
            writeByteBuffer.putInt(writeIntBuffer.get(i));
        }
        writeByteBuffer.clear();

        // ByteBuffer -> IntBuffer -> int[]
        ByteBuffer readByteBuffer = writeByteBuffer;
        IntBuffer readIntBuffer = readByteBuffer.asIntBuffer();
        int[] readData = new int[readIntBuffer.capacity()];
        readIntBuffer.get(readData);
        System.out.println("배열 복원: " + Arrays.toString(readData));
    }

}
