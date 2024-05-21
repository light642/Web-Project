package test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import test.entity.Room;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
}
