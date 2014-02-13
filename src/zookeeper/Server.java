package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class Server {
	private static final int CLIENT_PORT=2181; 
	private static final int CONNECTION_TIMEOUT=2000; 
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT, 
				 CONNECTION_TIMEOUT,new Watcher() { 
			            // 监控所有被触发的事件
			            public void process(WatchedEvent event) { 
			            } 
			        }); 
		 // 创建一个目录节点
		Stat stat=zk.exists("/testRootPath", false);
		if(null==stat){
		 zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
		   CreateMode.PERSISTENT); 
		}
		 // 创建一个子目录节点
		 zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
		   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 System.out.println("1111:"+new String(zk.getData("/testRootPath/testChildPathOne",false,null))); 
		 // 取出子目录节点列表
		 System.out.println(zk.getChildren("/testRootPath",true)); 
		 // 修改子目录节点数据
		 zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1); 
		 System.out.println("2222:"+new String(zk.getData("/testRootPath/testChildPathOne",false,null))); 
		 System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]"); 
		 zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne1".getBytes(),-1); 
		 // 创建另外一个子目录节点
		 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), 
		   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
		 // 删除子目录节点
		 zk.delete("/testRootPath/testChildPathTwo",-1); 
		 zk.delete("/testRootPath/testChildPathOne",-1); 
		 // 删除父目录节点
//		 Thread.currentThread().sleep(1000*10);
//		 zk.delete("/testRootPath",-1); 
		 // 关闭连接
		 zk.close(); 
	}
}
