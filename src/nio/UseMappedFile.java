package nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * 将文件映射到内存。
 * @version 1.00 2010-6-1, 15:40:12
 * @since 1.5
 * @author ZhangShixi
 */
public class UseMappedFile {

    private static final String IN_FILE = "C:\\copy.sql";
    private static final int POSITION = 0;
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(IN_FILE, "rw");
        FileChannel fcin = raf.getChannel();

        MappedByteBuffer buffer = fcin.map(MapMode.READ_WRITE, POSITION, SIZE);

        buffer.put(0, (byte) 97);
        buffer.put(1023, (byte) 122);

        raf.close();
        fcin.close();
    }
}
