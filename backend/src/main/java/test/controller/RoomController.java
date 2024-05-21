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
    String BASE_DIR = "C:\\Users\\Lenovo\\IdeaProjects\\demoSpring\\src\\main\\resources\\static\\images\\";
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/room/")
    public List<Room> getRoomList() {//用户获取房间列表
        return roomService.getRoom();
    }

    @GetMapping("/room/{id}")
    public Room getRoomDetail(@PathVariable int id) {//用户获取房间详情
        return roomService.getRoomDetail(id);
    }
    @PostMapping("/room")
    public boolean newRoom(Room room, MultipartFile file)
    {
        try{

            File uploadFile = new File(BASE_DIR + room.getId()+".jpg");
            file.transferTo(uploadFile);

        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        roomService.save(room);
        return true;
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