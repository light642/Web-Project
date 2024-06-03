package test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import test.entity.User;

public interface UserService extends IService<User> {
  List<UserService> getUserService(String username);
  List<UserService> getRoomService(int id);
}
