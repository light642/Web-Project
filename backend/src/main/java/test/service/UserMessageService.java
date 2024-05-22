package test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import test.entity.Reservation;
import test.entity.UserMessage;

import java.util.List;

public interface UserMessageService extends IService<UserMessage> {
    List<UserMessage> getMessage(String username);
    void addMessage(Reservation reservation);
}
