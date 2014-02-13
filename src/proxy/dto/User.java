package proxy.dto;

/**
 * @Title: User.java
 * @author : hongfei.xu@renren-inc.com
 * @Description: TODO(用一句话描述该文件做什么)
 * @date：2012-5-2 下午06:54:56
 * @version V1.0   
 */
public class User {
    private int id;
    private String name;
    public User(){
        
    }
    public User(int id,String name){
        this.id=id;
        this.name=name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "id:"+this.id+",name:"+this.name;
    }
}
