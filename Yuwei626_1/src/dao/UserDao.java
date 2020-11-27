package dao;

import user.User;

public interface UserDao {
	void save(User user);      //添加用户
	void delete(User user);    //删除用户
	void update(User user);    //修改用户信息
	User findById(int id);     //根据用户标识查找指定用户

}
