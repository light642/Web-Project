package test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import test.entity.RoomFilter;
import test.mapper.RoomFilterMapper;
@Service
public class RoomFilterServiceImpl extends ServiceImpl<RoomFilterMapper, RoomFilter> implements test.service.RoomFilterService {
    @Override
    public void clearRoomFilter(String username) {
        RoomFilter roomFilter = new RoomFilter();
        roomFilter.setUsername(username);
        updateById(roomFilter);
    }
}
