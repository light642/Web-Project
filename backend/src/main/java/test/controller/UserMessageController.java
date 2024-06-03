package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.entity.UserMessage;
import test.service.UserMessageService;

import java.util.List;

@RestController
public class UserMessageController {
    UserMessageService userMessageService;
    @GetMapping("/message")
    public List<UserMessage> getMessageList(@CookieValue String username){
        return userMessageService.getMessage(username);
    }
@Autowired
    public void setUserMessageService(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }
}
