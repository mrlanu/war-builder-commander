package io.lanu.travian.warbuildercommander.controllers;

import io.lanu.travian.warbuildercommander.RPCClient;
import io.lanu.travian.warbuildercommander.models.*;
import io.lanu.travian.warbuildercommander.services.WarSenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttacksController {

    private WarSenderService warSenderService;
    private RPCClient rpcClient;

    public AttacksController(WarSenderService warSenderService, RPCClient rpcClient) {
        this.warSenderService = warSenderService;
        this.rpcClient = rpcClient;
    }

    @PostMapping("/attacks/{villageName}")
    public String scheduleAttack(@PathVariable String villageName,
                                 @RequestBody List<AttackRequest> attackRequest){
        warSenderService.send(new CommandMessage(CommandsEnum.ATTACK, villageName, attackRequest));
        return "Attack has been created.";
    }

    @GetMapping("/villages/{viilageName}")
    public AboutVillageModel getAllVillages(@PathVariable String viilageName){
        return rpcClient.send(viilageName);
    }

}
