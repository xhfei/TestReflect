package proxy.service.impl;

import proxy.dto.User;
import proxy.service.UserService;


/**
 * @Title: UserServiceImpl.java
 * @author : hongfei.xu@renren-inc.com
 * @Description: TODO(用一句话描述该文件做什么)
 * @date：2012-5-2 下午06:57:47
 * @version V1.0   
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(int id) {
        User user=new User();
        user.setId(id);
        user.setName(id+"号");
        return user;
    }

    @Override
    public void saveUser(User user) {
       System.out.println("save user:"+user.toString());
    }

}
