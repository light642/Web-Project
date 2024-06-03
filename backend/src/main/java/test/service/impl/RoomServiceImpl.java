package test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.Reservation;
import test.entity.Room;
import test.mapper.ReservationMapper;
import test.mapper.RoomMapper;
import test.service.RoomService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    ReservationMapper reservationMapper;

    @Override
    public List<Room> getRoom(String roomType, String startTime, String endTime) {
        QueryWrapper<Reservation> reservationQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        List<Reservation> reservations=new ArrayList<>();
        if(startTime!=null&&endTime!=null&&!startTime.isEmpty()&&!endTime.isEmpty()) {
            reservationQueryWrapper.ge("end_time", startTime).le("start_time", endTime);
            reservations=reservationMapper.selectList(reservationQueryWrapper);
        }
        Collection<Integer> collection = new ArrayList<>();
        if(!reservations.isEmpty())
            for (Reservation reservation : reservations) {
                collection.add(reservation.getRoomId());
            }
        if (roomType!=null&&!roomType.isEmpty()&&!collection.isEmpty()) roomQueryWrapper.eq("room_type", roomType).notIn("id",collection);
        else if (roomType!=null&&!roomType.isEmpty()) roomQueryWrapper.eq("room_type", roomType);
        else if(!collection.isEmpty())roomQueryWrapper.notIn("id",collection);
        return getBaseMapper().selectList(roomQueryWrapper);
    }
    @Autowired
    public void setReservationMapper(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }
}
