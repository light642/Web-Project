package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.entity.Reservation;
import test.service.ReservationService;
import test.tag.AdminRequired;

import java.util.List;

@RestController
public class ReservationController {
    private ReservationService reservationService;
    @GetMapping("/reservation")
    public List<Reservation> userReservation(@CookieValue String username) {//用户预约列表
        return reservationService.getByUsername(username);
    }
    @GetMapping("/reservation/room/{id}")
    public List<Reservation> roomReservation(@PathVariable int id){
        return reservationService.getByRoomId(id);
    }
    @PostMapping("/reservation")//用户预约房间
    public boolean newReservation(Reservation reservation,@CookieValue String username) {
        List<Reservation> reservedlist = reservationService.getByRoomId(reservation.getRoomId());
        for(Reservation resed:reservedlist){
            if(!reservation.getStartTime().after(resed.getEndTime()) &&!reservation.getEndTime().before(resed.getStartTime())){
                return false;
            }
        }
        reservation.setUsername(username);
        reservation.setType("reservation");
        reservationService.save(reservation);
        return true;
    }

    @DeleteMapping("/reservation/{id}")//用户取消预约
    public void deleteReservation(@PathVariable int id){
        reservationService.removeById(id);
    }
    @AdminRequired
    @GetMapping("/blocks")
    public List<Reservation> roomReservation(){
        return reservationService.getBlock();
    }
    @AdminRequired
    @PostMapping("/block")
    public boolean blockRoom(Reservation reservation,@CookieValue String username){//管理员停用房间
        reservation.setType("block");
        reservation.setUsername(username);
        reservationService.save(reservation);
        reservationService.blockFor(reservation);
        return true;
    }
    @AdminRequired
    @DeleteMapping("/block/{id}")
    public boolean releaseRoom(@PathVariable int id){//管理员恢复房间使用
        reservationService.removeById(id);
        return true;
    }

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
