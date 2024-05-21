package test.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.RoomFilter;
import test.service.RoomFilterService;

@RestController
public class RoomFilterController {
    RoomFilterService roomFilterService;
    @PutMapping("/filter")
    public boolean updateFilter(RoomFilter roomFilter, @CookieValue String username){//用户更新筛选器
        roomFilter.setUsername(username);
        roomFilterService.updateById(roomFilter);
        return true;
    }
    @DeleteMapping("/filter")
    public boolean clearFilter(@CookieValue String username){//清空筛选
        roomFilterService.clearRoomFilter(username);
        return true;
    }
}
