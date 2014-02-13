package excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import excel.dto.School;
import excel.dto.User;

public class ReadRankData {
	private static Pattern PATTERN =Pattern.compile("^=========appid:\\d+=========school_id:\\d+=========$");
	public static void main(String...args) throws IOException{
		String path="C:\\Documents and Settings\\Administrator\\桌面\\数据\\app_rank_school_242225.txt.2013-12-16";
	}
	public List<User> readRank(String path,School school,int count) throws IOException, ParseException{
		List<User> list=new ArrayList<User>();
		InputStream in =null;
		BufferedReader reader=null;
		File file=new File(path);
		try {
			in =new FileInputStream(file);
			reader=new BufferedReader(new InputStreamReader(in));
			String line=null;
			boolean read=false;
			while ((line=reader.readLine())!=null){
				boolean Separator=isSchoolSeparator(line);
				if(Separator){
					if(read && line.indexOf(String.valueOf(school.getSchoolId()))==-1){
						break;
					}
					if(line.indexOf(String.valueOf(school.getSchoolId()))==-1){
						continue;
					}
					if(read){
						read=false;
						break;
					}else{
						read=true;
					}
					continue;
				}
				if(read && !"".equals(line) && line.indexOf("uid")<0){
					StringTokenizer stringTokenizer=new StringTokenizer(line);
					User user=new User();
					String[] row=new String[6];
					int index=0;
					while(stringTokenizer.hasMoreElements()){
						String str= stringTokenizer.nextToken();
						row[index++]=str;
					}
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						user.setUid(Integer.parseInt(row[0]));
					} catch (Exception e) {
						System.out.println(school.getSchoolId()+"|"+Separator+"|"+read+"|"+line.indexOf(String.valueOf(school.getSchoolId()))+"-----"+line);
					}
					user.setAppid(Integer.parseInt(row[1]));
					user.setScore(Integer.parseInt(row[2]));
					user.setSchoolId(Integer.parseInt(row[3]));
					user.setPlayTime(sdf.parse(row[4]+" "+row[5]));
					list.add(user);
					if(list.size()>=count){
						break;
					}
				}
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					reader=null;
				}
			}
		}
		return list;
	}
	private boolean isSchoolSeparator(String line){
		Matcher m = PATTERN.matcher(line);
		return m.matches();
	}
}
