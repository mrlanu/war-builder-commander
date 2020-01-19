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

    @PostMapping("/attacks")
    public String scheduleAttack(@RequestBody AttackRequest attackRequest){
        warSenderService.send(new CommandMessage(attackRequest.getPlayerId(), CommandsEnum.ATTACK, attackRequest));
        return "Attack has been created.";
    }

    @PostMapping("/spams")
    public String sendSpam(@RequestBody AttackRequest attackRequest){
        warSenderService.send(new CommandMessage(attackRequest.getPlayerId(), CommandsEnum.SPAM, attackRequest));
        return "Spam has been sent.";
    }

    @GetMapping("/villages/{playerId}")
    public AboutVillageModel getAllVillages(@PathVariable String playerId){
        return rpcClient.sendReqForAllVillage(playerId);
    }

    @GetMapping("/villages/{playerId}/{villageName}")
    public AboutVillageModel getAvailableTroops(@PathVariable String playerId, @PathVariable String villageName){
        return rpcClient.sendReqForAvailableTroops(playerId, villageName);
    }

}
