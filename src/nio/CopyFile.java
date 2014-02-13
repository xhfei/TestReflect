package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将一个文件的所有内容拷贝到另一个文件中。
 * 
 * CopyFile.java 执行三个基本操作：
 * 首先创建一个 Buffer，然后从源文件中将数据读到这个缓冲区中，然后将缓冲区写入目标文件。
 * 程序不断重复 — 读、写、读、写 — 直到源文件结束。
 * 
 * @version 1.00 2010-5-19, 10:49:46
 * @since 1.5
 * @author ZhangShixi
 */
public class CopyFile {

    private static final String IN_FILE = "C:\\copy.sql";
    private static final String OUT_FILE = "C:\\copy.txt";

    public static void main(String[] args) throws Exception {
        // 获取源文件和目标文件的输入输出流
        FileInputStream fin = new FileInputStream(IN_FILE);
        FileOutputStream fout = new FileOutputStream(OUT_FILE);

        // 获取输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            // clear方法重设缓冲区，使它可以接受读入的数据
            buffer.clear();

            // 从输入通道中将数据读到缓冲区
            int r = fcin.read(buffer);

            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
            if (r == -1) {
                break;
            }

            // flip方法让缓冲区可以将新读入的数据写入另一个通道
            buffer.flip();

            // 从输出通道中将数据写入缓冲区
            fcout.write(buffer);
        }

        fcin.close();
        fcout.close();
        fin.close();
        fout.close();
    }
}
