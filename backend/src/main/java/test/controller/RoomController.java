package test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.entity.Room;
import test.service.RoomService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class RoomController {
    private RoomService roomService;
    String BASE_DIR = "C:\\Users\\Lenovo\\Documents\\Workspace\\Web Project\\backend\\src\\main\\resources\\static\\images\\";
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/room")
    public List<Room> getRoomList(String roomType, String startTime, String endTime) {
        return roomService.getRoom(roomType,startTime,endTime);
    }

    @GetMapping("/room/{id}")
    public Room getRoomDetail(@PathVariable int id) {
        return roomService.getById(id);
    }
    @PostMapping("/room")
    public void newRoom(Room room, MultipartFile file){
        try{
            roomService.save(room);
            System.out.println(BASE_DIR + room.getId()+".jpg");
            File uploadFile = new File(BASE_DIR + room.getId()+".jpg");
            uploadFile.createNewFile();
            file.transferTo(uploadFile);

        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }
    @DeleteMapping("/room/{id}")
    public boolean deleteRoom(@PathVariable int id){
        roomService.removeById(id);
        File deletingFile = new File(BASE_DIR+id+".jpg");
        return deletingFile.delete();
    }
    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}