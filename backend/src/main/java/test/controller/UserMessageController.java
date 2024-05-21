package test.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
