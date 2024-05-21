package test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import test.entity.Reservation;
@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
}
