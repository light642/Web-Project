package test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import test.entity.RoomFilter;

public interface RoomFilterService extends IService<RoomFilter> {
    public void clearRoomFilter(String username);
}
