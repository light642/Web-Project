package test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import test.entity.Reservation;
import test.entity.UserMessage;
import test.mapper.UserMessageMapper;
import test.service.UserMessageService;

import java.util.List;
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {
    @Override
    public List<UserMessage> getMessage(String  username) {
        QueryWrapper<UserMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public void addMessage(Reservation reservation) {
        UserMessage userMessage =new UserMessage();
        userMessage.setUsername(reservation.getUsername());
        userMessage.setMessage("您预约从"+reservation.getStartTime()+"到"+reservation.getEndTime()+"的房间("+reservation.getRoomId()+")由于维修等原因无法使用，已自动取消，请重新预约");
        getBaseMapper().insert(userMessage);
    }
}
