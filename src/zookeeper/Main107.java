package zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class Main107 {
	private static final int CLIENT_PORT=2181; 
	private static final int CONNECTION_TIMEOUT=2000; 
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 创建一个与服务器的连接
		 ZooKeeper zk = new ZooKeeper("10.3.24.126:" + CLIENT_PORT, 
				 CONNECTION_TIMEOUT, new Watcher() { 
		            // 监控所有被触发的事件
		            public void process(WatchedEvent event) { 
		                System.out.println("已经触发了" + event.getType() + "事件！"); 
		            } 
		        }); 
		 // 创建一个目录节点
		 List<String> list=zk.getChildren("/root", false);
		 if(null!=list && !list.isEmpty()){
			 for(String c:list){
				 System.out.println("节点："+c);
				 List<String> childrenList=zk.getChildren("/root/"+c, false);
				 if(null==childrenList || childrenList.isEmpty()){
					 System.out.println(c+"没有子节点");
				 }else{
					 for(String nc:childrenList){
						 System.out.println("    "+nc); 
					 }
				 }
			 }
		 }else{
			 System.out.println("没有子节点");
		 }
		 
		 // 关闭连接
		 zk.close(); 
	}

}
