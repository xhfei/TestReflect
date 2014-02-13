package monitor;

public class TestForumServiceByCglib {
	public static void main(String[] args) {  
	      CglibProxy proxy = new CglibProxy();
//	      ①  
	      ForumServiceImpl forumService = 
	                (ForumServiceImpl )proxy.getProxy(ForumServiceImpl.class);  
	      forumService.removeForum(10);  
	      forumService.removeTopic(1023);  
	    }  
}
