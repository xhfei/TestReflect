package zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class Client {
	private static final int CLIENT_PORT=2181; 
	private static final int CONNECTION_TIMEOUT=2000; 
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT, 
				 CONNECTION_TIMEOUT,null); 
		Watcher watcher=new Watcher() { 
            // 监控所有被触发的事件
            public void process(WatchedEvent event) { 
            	System.out.println("已经触发了" + event.getType() + "事件！"); 
            	ZooKeeper zooKeeper=null;
				try {
					zooKeeper = new ZooKeeper("localhost:" + CLIENT_PORT, 
						 CONNECTION_TIMEOUT,null);
					System.out.println(zooKeeper.getChildren("/testRootPath",true));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            } 
		};
		 // 创建一个目录节点
		Stat stat=zk.exists("/testRootPath", false);
		if(null==stat){
			 zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
					   CreateMode.PERSISTENT); 
		}
		List<String> list = zk.getChildren("/testRootPath", watcher);
		while(true){
			
		}
	}
}
