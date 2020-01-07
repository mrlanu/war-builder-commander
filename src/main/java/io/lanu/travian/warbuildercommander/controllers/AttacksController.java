package io.lanu.travian.warbuildercommander.controllers;

import io.lanu.travian.warbuildercommander.RPCClient;
import io.lanu.travian.warbuildercommander.models.AboutVillageModel;
import io.lanu.travian.warbuildercommander.models.AttackRequest;
import io.lanu.travian.warbuildercommander.models.CommandMessage;
import io.lanu.travian.warbuildercommander.models.CommandsEnum;
import io.lanu.travian.warbuildercommander.services.WarSenderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttacksController {

    private WarSenderService warSenderService;
    private RPCClient rpcClient;

    public AttacksController(WarSenderService warSenderService, RPCClient rpcClient) {
        this.warSenderService = warSenderService;
        this.rpcClient = rpcClient;
    }

    @GetMapping("/wakeup")
    public String wakeup(){
        return "I'm ready, my commander";
    }

    @PostMapping("/attacks")
    public String scheduleAttack(@RequestBody AttackRequest attackRequest){
        warSenderService.send(new CommandMessage(CommandsEnum.ATTACK, attackRequest));
        return "Attack has been created.";
    }

    @GetMapping("/villages/{villageName}")
    public AboutVillageModel getAllVillages(@PathVariable String villageName){
        return rpcClient.send(villageName);
    }

}
