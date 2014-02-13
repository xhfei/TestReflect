package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用分散和聚集IO。
 * @version 1.00 2010-6-1, 16:12:38
 * @since 1.5
 * @author ZhangShixi
 */
public class UseScatterGather {

    private static final int PORT = 8099;
    private static final int FIRST_HEADER_LENGTH = 2;
    private static final int SECOND_HEADER_LENGTH = 4;
    private static final int BODY_LENGTH = 6;

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(PORT);
        ssc.socket().bind(address);

        int length = FIRST_HEADER_LENGTH + SECOND_HEADER_LENGTH + BODY_LENGTH;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(FIRST_HEADER_LENGTH);
        buffers[1] = ByteBuffer.allocate(SECOND_HEADER_LENGTH);
        buffers[2] = ByteBuffer.allocate(BODY_LENGTH);

        SocketChannel sc = ssc.accept();

        while (true) {
            // Scatter-read into buffers
            int bytesRead = 0;
            while (bytesRead < length) {
                long r = sc.read(buffers);
                bytesRead += r;

                System.out.println("r: " + r);
                ByteBuffer bb;
                for (int i = 0; i < buffers.length; i++) {
                    bb = buffers[i];
                    System.out.println(
                            "b " + i + " " + bb.position() + " " + bb.limit());
                }
            }

            // Process message here

            // Flip buffers
            for (ByteBuffer bb : buffers) {
                bb.flip();
            }

            // Scatter-write back out
            int bytesWrite = 0;
            while (bytesWrite < length) {
                long w = sc.write(buffers);
                bytesWrite += w;
            }

            // Clear buffers
            for (ByteBuffer bb : buffers) {
                bb.clear();
            }

            System.out.println(bytesRead + " " + bytesWrite + " " + length);
        }
    }
}
