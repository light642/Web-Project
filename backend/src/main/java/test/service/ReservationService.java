package test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import test.entity.Reservation;

import java.util.List;

public interface ReservationService extends IService<Reservation> {
    List<Reservation> getByUsername(String username);
    List<Reservation> getByRoomId(int id);
    void blockFor(Reservation reservation);

}
