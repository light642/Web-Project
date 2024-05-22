package test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import test.entity.Room;

import java.util.List;

public interface RoomService extends IService<Room> {
    List<Room> getRoom(String roomType,String startTime,String endTime);
}
