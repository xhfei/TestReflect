package proxy.aop;

import org.springframework.stereotype.Service;

import proxy.dto.User;
@Service("myService")
public class MyServiceImpl implements IMyService{

	@Override
	public User m1(User u, String id) {
		System.out.println("m1 executed!");
		if(id==null || "".equals(id)){
			return u;
		}else {
			return new User(1,"Hello World!");
		}
	}

	@Override
	public User m2(User u, String id) {
		System.out.println("m2 executed!");
		if(id==null || "".equals(id)){
			return u;
		}else {
			return new User(2,"Hello World!");
		}
	}

}
