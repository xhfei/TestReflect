package threadLocal;

import java.sql.Connection;
import java.sql.Statement;

public class TopicDao {
	// ①使用ThreadLocal保存Connection变量
	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

	public static Connection getConnection() {

		// ②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection，
		// 并将其保存到线程本地变量中。
		if (connThreadLocal.get() == null) {
//			Connection conn = ConnectionManager.getConnection();
			Connection conn = null;
			connThreadLocal.set(conn);
			return conn;
		} else {
			// ③直接返回线程本地变量
			return connThreadLocal.get();
		}
	}

	public void addTopic() {

		// ④从ThreadLocal中获取线程对应的
//		Statement stat = getConnection().createStatement();
	}
}
