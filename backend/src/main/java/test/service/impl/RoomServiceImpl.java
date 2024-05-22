package test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import test.entity.Room;
import test.mapper.RoomMapper;
import test.service.RoomService;

import java.util.List;
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public List<Room> getRoom(String roomType, String startTime, String endTime) {
        //待实现的筛选
        return getBaseMapper().selectList(null);
    }
}
