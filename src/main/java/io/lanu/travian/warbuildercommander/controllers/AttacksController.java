package io.lanu.travian.warbuildercommander.controllers;

import io.lanu.travian.warbuildercommander.models.AttackRequest;
import io.lanu.travian.warbuildercommander.models.CommandMessage;
import io.lanu.travian.warbuildercommander.models.CommandsEnum;
import io.lanu.travian.warbuildercommander.services.WarSenderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/attacks")
public class AttacksController {

    private WarSenderService warSenderService;

    public AttacksController(WarSenderService warSenderService) {
        this.warSenderService = warSenderService;
    }

    @PostMapping("/attacks/{villageName}")
    public String scheduleAttack(@PathVariable String villageName,
                                 @RequestBody List<AttackRequest> attackRequest){
        warSenderService.send(new CommandMessage(CommandsEnum.ATTACK, villageName, attackRequest));
        return "Attack has been created.";
    }

}
