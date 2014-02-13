package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import proxy.dto.User;
import proxy.invocation.UserInvocationHandler;
import proxy.service.UserService;
import proxy.service.impl.UserServiceImpl;

/**
 * @Title: test.java
 * @author : hongfei.xu@renren-inc.com
 * @Description: TODO(用一句话描述该文件做什么)
 * @date：2012-5-2 下午06:58:54
 * @version V1.0   
 */
public class test {
    public static void main(String...args){
        final UserService userServie=new UserServiceImpl();
        Method[] methods=UserService.class.getDeclaredMethods();
        for(Method md:methods){
        	Class class1=md.getDeclaringClass();
        	System.out.println("111111:"+class1.getSimpleName());
        }
        InvocationHandler handler=new UserInvocationHandler(userServie);
        UserService proxy=(UserService)Proxy.newProxyInstance(userServie.getClass().getClassLoader(), new Class[]{UserService.class}, handler);
        proxy.saveUser(new User());
        User user=proxy.getUser(1);
        System.out.println(user.getName());
    }
}
