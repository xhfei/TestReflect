package test;

import java.io.File;
import java.io.FileWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class TestMongo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Mongo mongo = null;
		try {
			Set<Integer> set=new HashSet<Integer>();
			FileWriter fileWriter=new FileWriter(new File("C:\\id.txt"));
//			String[] hostItem={"10.3.16.180:40001","10.3.24.36:40001","10.3.16.102:40001"};
			MongoOptions mongoOptions=new MongoOptions();
			mongoOptions.connectionsPerHost=20;
			mongoOptions.threadsAllowedToBlockForConnectionMultiplier=10;
			String[] hostItem={"10.3.24.36:40001","10.3.16.102:40001","10.3.16.180:40001"};
			List<ServerAddress> addList = new ArrayList<ServerAddress>();
			for(String host:hostItem){
				String[] hostPort = host.split(":");
				addList.add(new ServerAddress(hostPort[0], Integer.valueOf(hostPort[1])));
			}
			mongo = new Mongo(addList,mongoOptions);
			mongo.setWriteConcern(WriteConcern.SAFE);
			MongoOptions mo=mongo.getMongoOptions();
			System.out.println("mo.alwaysUseMBeans:"+mo.alwaysUseMBeans);
			System.out.println("mo.connectTimeout:"+mo.connectTimeout);
			System.out.println("mo.maxAutoConnectRetryTime:"+mo.maxAutoConnectRetryTime);
			System.out.println("mo.maxWaitTime:"+mo.maxWaitTime);
			System.out.println("mo.socketTimeout:"+mo.socketTimeout);
			System.out.println("mo.connectionsPerHost:"+mo.connectionsPerHost);
			System.out.println("mo.threadsAllowedToBlockForConnectionMultiplier:"+mo.threadsAllowedToBlockForConnectionMultiplier);
			DB db = mongo.getDB("fuxi");
			ReadPreference preference = ReadPreference.primary();
			db.setReadPreference(preference);
			long begin=System.currentTimeMillis();
			for(int i=0;i<100;i++){
				DBCollection collection=db.getCollection(String.format("friends_play_list_%d",i));
				DBCursor cursor=collection.find().limit(10000);
				while (cursor.hasNext()) {
					BasicDBObject dbObject = (BasicDBObject) cursor.next();
					long unionIdObj =  dbObject.getLong("_id");
//					for (String userIdKey : unionIdObj.keySet()) {
//						long unionId = NumberUtils.toLong(userIdKey);
						int userId=TestNumberUtil.separateLong2int(unionIdObj,false);
						System.out.println(userId);
//						if(!set.contains(userId)){
//							set.add(userId);
//							fileWriter.write(new String(String.valueOf(userId).getBytes(),"UTF-8"));
//							fileWriter.write("\n");
//							System.out.println(userId);
//						}
//					}
				}
			}
			System.out.println("time cost:"+(System.currentTimeMillis()-begin));
//			writeMongo(mongo,223157279,222188852,2081143);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			mongo.close();
		}
	}
	public static void writeMongo(Mongo mongo,int userId,int friendId,int appId){
		 BasicDBObject updateQuery = new BasicDBObject();
         // userid高位，appid低位
         updateQuery.put("_id", TestNumberUtil.combineInt2Long(userId, appId));
         BasicDBObject updateCommand = new BasicDBObject("$set", new BasicDBObject(
         		String.format("%s.%s", "user_id_list", friendId), System.currentTimeMillis()));
         WriteResult result = mongo.getDB("fuxi").getCollection("friends_play_list_79").update(updateQuery,updateCommand, true, true);
         System.out.println("res:"+result.getN());
	}

}
