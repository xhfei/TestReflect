package excel.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private int uid;
	private int appid;
	private int score;
	private int schoolId;
	private Date playTime;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	@Override
	public String toString() {
		return ""+uid+","+appid+","+score+","+schoolId+","+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(playTime)+"";
	}
	
}
