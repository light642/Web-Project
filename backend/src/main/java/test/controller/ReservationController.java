package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.entity.Reservation;
import test.service.ReservationService;

import java.util.List;

@RestController
public class ReservationController {
    private ReservationService reservationService;
    @GetMapping("/reservation/{username}")
    public List<Reservation> userReservation(@PathVariable String username) {//用户预约列表
        return reservationService.getByUsername(username);
    }

    @GetMapping("/reservation/room/{id}")
    public List<Reservation> roomReservation(@PathVariable int id){
        return reservationService.getByRoomId(id);
    }
    @PostMapping("/reservation")//用户预约房间
    public boolean newReservation(Reservation reservation) {
        reservation.setType("reservation");
        reservationService.save(reservation);
        return true;
    }

    @DeleteMapping("/reservation/{id}")//用户取消预约
    public void deleteReservation(@PathVariable int id){
        reservationService.removeById(id);
    }

    @GetMapping("/block")
    public List<Reservation> roomReservation(){
        return reservationService.getBlock();
    }
    @PostMapping("/block")
    public boolean blockRoom(Reservation reservation){//管理员停用房间
        reservation.setType("block");
        reservationService.save(reservation);
        reservationService.blockFor(reservation);
        return true;
    }
    @DeleteMapping("/block")
    public boolean releaseRoom(int id){//管理员恢复房间使用
        reservationService.removeById(id);
        return true;
    }

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
