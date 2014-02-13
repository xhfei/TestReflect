package proxy.aop;

import proxy.dto.User;

public interface IMyService {
	public User m1(User u,String id);
	public User m2(User u,String id);
}
