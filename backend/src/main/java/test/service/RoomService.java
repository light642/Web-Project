package test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import test.entity.Room;

import java.util.List;

public interface RoomService extends IService<Room> {
    public List<Room> getRoom();
    public Room getRoomDetail(int id);
}
