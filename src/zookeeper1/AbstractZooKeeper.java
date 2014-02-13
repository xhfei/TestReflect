package zookeeper1;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class AbstractZooKeeper implements Watcher {  
    private static final int SESSION_TIME   = 2000;  
    protected ZooKeeper  zooKeeper;  
  
    public void connect(String hosts) throws IOException, InterruptedException{  
        zooKeeper = new ZooKeeper(hosts,SESSION_TIME,this);  
    }  
    public void process(WatchedEvent event) {  
    	try {
    		System.out.println("root 节点数据有变化:"+event.getType());
    		Stat stat=zooKeeper.exists("/root", false);
    		if(null==stat){
    			 this.zooKeeper.create("/root", "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT/*此处创建的为持久态的节点,可为瞬态*/);  
    		}
			List<String> list=zooKeeper.getChildren("/root", this) ;
			if (list.isEmpty()) {  
				System.out.println("root 节点没有数据");
                return;  
            }else{  
            	System.out.println("root 节点数据如下：");
                for(String child: list){  
                    System.out.println("child:"+child);  
                }  
            }  
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }  
    public void close() throws InterruptedException{  
        zooKeeper.close();  
    }  
}  
