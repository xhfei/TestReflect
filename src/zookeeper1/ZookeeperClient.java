package zookeeper1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZookeeperClient {
	private static ZooKeeper zookeeper=null;
	private static CountDownLatch latch=null;
	public static void main(String[] args) throws Exception {
		  ZooKeeper zk = ZookeeperClient.getZooKeeper();
		  long sessionId = zk.getSessionId();
		  System.out.println("sessionId:"+sessionId);
		  new ZooKeeper("10.9.18.107:2181" , 30000, null, sessionId, null).close();
		  Thread.sleep(10000L);

		  // rebuild a new session
		  long newSessionid = ZookeeperClient.getZooKeeper().getSessionId();
		  System.out.println("newSessionid:"+newSessionid);
		  // check the new session
		  String status = newSessionid != sessionId ? "OK" : "FAIL";
		  System.out.println(String.format("%s --> %s %s", sessionId, newSessionid, status));

		  // close the client
		  ZookeeperClient.getZooKeeper().close();
		}
	public static org.apache.zookeeper.ZooKeeper getZooKeeper() {
		  if (zookeeper == null) {
		    synchronized (ZookeeperClient.class) {
		      if (zookeeper == null) {
		        latch = new CountDownLatch(1);
		        zookeeper = buildClient();//如果失败，下次还有成功的机会
		        long startTime = System.currentTimeMillis();
		        try {
		           latch.await(2, TimeUnit.SECONDS);
		        } catch (InterruptedException e) {
		           e.printStackTrace();
		        } finally {
		          System.out.println("[SUC-CORE] init cost: " + (System.currentTimeMillis() - startTime) + "(ms) ");
		          latch = null;
		        }
		      }
		    }
		  }
		  return zookeeper;
		}
	private static ZooKeeper buildClient() {
	    try {
	        return new ZooKeeper("10.9.18.107:2181", 30000, new SessionWatcher());
	    } catch (IOException e) {
	        throw new RuntimeException("init zookeeper fail.", e);
	    }
	}
	/**
	 * 关闭zookeeper连接，释放资源
	 */
	public static void close() {
	    System.out.println("[SUC-CORE] close");
	    if (zookeeper != null) {
	        try {
	            zookeeper.close();
	            zookeeper = null;
	        } catch (InterruptedException e) {
	            //ignore exception
	        }
	    }
	}
	static class SessionWatcher implements Watcher {

	    public void process(WatchedEvent event) {
	        if (event.getState() == KeeperState.SyncConnected) {
	            if (latch != null) {
	                latch.countDown();
	            }
	        } else if (event.getState() == KeeperState.Expired) {
	            System.out.println("[SUC-CORE] session expired. now rebuilding...");

	            //session expired, may be never happending.
	            //close old client and rebuild new client
	            close();

	            getZooKeeper();
	        }
	    }
	}
}
