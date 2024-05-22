package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.entity.UserMessage;
import test.service.UserMessageService;

import java.util.List;

@RestController
public class UserMessageController {
    UserMessageService userMessageService;
    @GetMapping("/message/{username}")
    public List<UserMessage> getMessageList(@PathVariable String username){
        return userMessageService.getMessage(username);
    }
@Autowired
    public void setUserMessageService(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }
}
