package io.lanu.travian.warbuildercommander.controllers;

import io.lanu.travian.warbuildercommander.RPCClient;
import io.lanu.travian.warbuildercommander.models.AttackRequest;
import io.lanu.travian.warbuildercommander.models.CommandMessage;
import io.lanu.travian.warbuildercommander.models.CommandsEnum;
import io.lanu.travian.warbuildercommander.models.VillageModel;
import io.lanu.travian.warbuildercommander.services.WarSenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attacks")
public class AttacksController {

    private WarSenderService warSenderService;
    private RPCClient rpcClient;

    public AttacksController(WarSenderService warSenderService, RPCClient rpcClient) {
        this.warSenderService = warSenderService;
        this.rpcClient = rpcClient;
    }

    @PostMapping("/{villageName}")
    public String scheduleAttack(@PathVariable String villageName,
                                 @RequestBody List<AttackRequest> attackRequest){
        warSenderService.send(new CommandMessage(CommandsEnum.ATTACK, villageName, attackRequest));
        return "Attack has been created.";
    }

    @GetMapping
    public List<VillageModel> getAllVillages(){
        return rpcClient.send();
    }

}
