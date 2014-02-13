package proxy.service;

import proxy.dto.User;

/**
 * @Title: UserService.java
 * @author : hongfei.xu@renren-inc.com
 * @Description: TODO(用一句话描述该文件做什么)
 * @date：2012-5-2 下午06:54:13
 * @version V1.0   
 */
public interface UserService {
    public void saveUser(User user);
    public User getUser(int id);
}
