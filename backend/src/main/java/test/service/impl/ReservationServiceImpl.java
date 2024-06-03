package test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import test.entity.Reservation;
import test.mapper.ReservationMapper;
import test.service.ReservationService;
import test.service.UserMessageService;

import java.sql.Date;
import java.util.List;
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper,Reservation> implements ReservationService {
    UserMessageService userMessageService;
    @Override
    public List<Reservation> getByUsername(String username) {
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<Reservation> getByRoomId(int id) {
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id",id);
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<Reservation> getBlock() {
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type","block");
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public void blockFor(Reservation reservation) {
        //预约的结束时间晚于停用开始时间且预约的开始时间早于停用结束时间时，删除预约，向用户发送通知
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type","reservation").eq("room_id",reservation.getRoomId()).ge("end_time",reservation.getStartTime()).le("start_time",reservation.getEndTime());
        List<Reservation> blockedReservations = getBaseMapper().selectList(queryWrapper);
        for(Reservation el : blockedReservations){
            getBaseMapper().deleteById(el.getId());
            userMessageService.addMessage(el);
        }
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void clearReservation(){
        java.util.Date date = new java.util.Date();
        Date now=new Date(date.getTime());
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("endTime",now);
        List<Reservation> overdueReservations = getBaseMapper().selectList(queryWrapper);
        for(Reservation res : overdueReservations) getBaseMapper().deleteById(res.getId());
    }
    @Autowired
    public void setUserMessageService(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }
}
