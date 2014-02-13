package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用直接字节缓冲区将一个文件的所有内容拷贝到另一个文件中。
 * @version 1.00 2010-6-1, 15:21:42
 * @since 1.5
 * @author ZhangShixi
 */
public class FastCopyFile {

    private static final String IN_FILE = "C:\\copy.sql";
    private static final String OUT_FILE = "C:\\copy.txt";

    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream(IN_FILE);
        FileOutputStream fout = new FileOutputStream(OUT_FILE);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);

            if (r == -1) {
                break;
            }

            buffer.flip();
            fcout.write(buffer);
        }

        fcin.close();
        fcout.close();
        fin.close();
        fout.close();
    }
}
