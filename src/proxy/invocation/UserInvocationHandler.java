package proxy.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import proxy.service.UserService;

/**
 * @Title: UserInvocationHandler.java
 * @author : hongfei.xu@renren-inc.com
 * @Description: TODO(用一句话描述该文件做什么)
 * @date：2012-5-2 下午07:03:35
 * @version V1.0   
 */
public class UserInvocationHandler implements InvocationHandler{
    private UserService userService;
    public  UserInvocationHandler(UserService userService){
        this.userService=userService;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:"+proxy.getClass().getName());
        System.out.println("method:"+method.getName());
        Object res=method.invoke(userService, args);
        return res;
    }
}
