package excel;

import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.List;

import excel.dto.School;
import excel.dto.User;

public class SchoolRankMain {

	/**
	 * @param args
	 */
	/**
	public static void main(String[] args)throws Exception {
		ReadExcel xlsxMain = new ReadExcel();
		String fileName = "C:\\Documents and Settings\\Administrator\\桌面\\数据\\重点大学.xlsx";
		String path="C:\\Documents and Settings\\Administrator\\桌面\\数据\\app_rank_school_242225.txt.2013-12-16";
		String newpath="C:\\Documents and Settings\\Administrator\\桌面\\数据\\";
		List<School> list=xlsxMain.readXlsx(fileName);
		for(School sc:list){
			ReadRankData readRankData=new ReadRankData();
			List<User> userList=readRankData.readRank(path, sc, 30);
			System.out.println("================="+sc+","+userList.size());
			String[] rank={"1-10","11-20","21-30"};
			createFile(newpath,sc,userList,true,rank,"重点大学");
		}
		System.out.println(list.size());
	}*/
	/**
	public static void main(String[] args)throws Exception {
		ReadExcel xlsxMain = new ReadExcel();
		String fileName = "C:\\Documents and Settings\\Administrator\\桌面\\数据\\其他大学.xlsx";
		String path="C:\\Documents and Settings\\Administrator\\桌面\\数据\\app_rank_school_242225.txt.2013-12-16";
		String newpath="C:\\Documents and Settings\\Administrator\\桌面\\数据\\其他大学\\";
		List<School> list=xlsxMain.readXlsx(fileName);
		for(School sc:list){
			ReadRankData readRankData=new ReadRankData();
			List<User> userList=readRankData.readRank(path, sc, 30);
			System.out.println("================="+sc+","+userList.size());
			String[] rank={"1-5","6-10","11-20","21-30"};
			createFile(newpath,sc,userList,true,rank,"其他大学");
		}
		System.out.println(list.size());
	}*/
	public static void main(String[] args)throws Exception {
		ReadExcel xlsxMain = new ReadExcel();
		String fileName = "C:\\Documents and Settings\\Administrator\\桌面\\数据\\其他大学.xlsx";
		String path="C:\\Documents and Settings\\Administrator\\桌面\\数据\\app_rank_school_242225.txt.2013-12-16";
		String newpath="C:\\Documents and Settings\\Administrator\\桌面\\数据\\总数据\\";
		List<School> list=xlsxMain.readXlsx(fileName);
		for(School sc:list){
			ReadRankData readRankData=new ReadRankData();
			List<User> userList=readRankData.readRank(path, sc, 30);
			System.out.println("================="+sc+","+userList.size());
			String[] rank={""};
//			String[] rank={"1-5","6-10","11-20","21-30"};
			createOneFile(newpath,sc,userList,true,rank,"所有大学");
		}
		System.out.println(list.size());
	}
	public static void createOneFile(String newpath,School sc,List<User> userList,boolean ismain,String[] fileIndex,String filePrefix) throws IOException{
			String fileName=filePrefix+".txt";
			File file=new File(newpath+fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter writer=null;
			OutputStream out=new FileOutputStream(file,true);
			writer=new BufferedWriter(new OutputStreamWriter(out));
			String title="=========school_id:"+sc.getSchoolId()+"=========";
			writer.write(title);
			writer.write("\n");
			writer.write("uid	appid	score	school_id	achievement_time");
			writer.write("\n");
			for(User user:userList){
//				String uidStr=URLEncoder.encode(String.valueOf(user.getUid()),"utf-8");
//				String appidStr=URLEncoder.encode(String.valueOf(user.getAppid()),"utf-8");
//				String scoreStr=URLEncoder.encode(String.valueOf(user.getScore()),"utf-8");
//				String schoolidStr=URLEncoder.encode(String.valueOf(user.getSchoolId()),"utf-8");
//				String scoreStr=URLEncoder.encode(String.valueOf(user.getPlayTime()),"utf-8");
				writer.write(user.toString());
				writer.write("\n");
			}
			writer.flush();
			writer.close();
	}
	/**
	public static void createFile(String newpath,School sc,List<User> userList,boolean ismain,String[] fileIndex,String filePrefix) throws IOException{
		for(String index:fileIndex){
			String[] ind=index.split("-");
			int begin=Integer.parseInt(ind[0])-1;
			if(begin>=(userList.size())){
				return;
			}
			String fileName=filePrefix+" "+index+".txt";
			File file=new File(newpath+fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			int end=Integer.parseInt(ind[1])-1;
			BufferedWriter writer=null;
			OutputStream out=new FileOutputStream(file,true);
			writer=new BufferedWriter(new OutputStreamWriter(out));
			writer.write("=========school_id:data=========".replace("data", sc.getSchoolId()));
			writer.write("\n");
			for(int i=begin;i<=end;i++){
				if(i>=userList.size()){
					break;
				}
				User user=userList.get(i);
				String uidStr=URLEncoder.encode(String.valueOf(user.getUid()),"utf-8");
				writer.write(uidStr);
				writer.write("\n");
			}
			writer.flush();
			writer.close();
		}
	}*/
}
