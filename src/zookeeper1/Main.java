package zookeeper1;

import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.data.Stat;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 try {  
	            ZooKeeperOperator zkoperator= new ZooKeeperOperator();  
	            zkoperator.connect("localhost:2181");  
	            byte[] data = new byte[]{'d','a','t','a'};  
	            
	            
	            Stat statChild1=zkoperator.zooKeeper.exists("/root/child1", false);
	    		if(null!=statChild1){
	    			zkoperator.del("/root/child1", -1);
	    		}
	    		Stat statChild2=zkoperator.zooKeeper.exists("/root/child2", false);
	    		if(null!=statChild2){
	    			zkoperator.del("/root/child2", -1);
	    		}
	    		Stat stat=zkoperator.zooKeeper.exists("/root", false);
	    		if(null==stat){
	    			zkoperator.create("/root","root".getBytes());  
	    		}
	            zkoperator.setWatcher("/root", zkoperator);
	            System.out.println(Arrays.toString(zkoperator.getData("/root")));  
	    		zkoperator.zooKeeper.setData("/root","123".getBytes(), -1);
	            zkoperator.create("/root/child1",data);  
//	            System.out.println(Arrays.toString(zkoperator.getData("/root/child1")));  
	              
	            zkoperator.create("/root/child2",data);  
//	            System.out.println(Arrays.toString(zkoperator.getData("/root/child2")));  
	              
//	            System.out.println("节点孩子信息:");  
	            zkoperator.getChild("/root");  
	              
	            zkoperator.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}

}
