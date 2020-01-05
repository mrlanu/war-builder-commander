package io.lanu.travian.warbuildercommander.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommandMessage implements Serializable {
    private CommandsEnum command;
    private String villageName;
    private List<AttackRequest> attackRequests;

    public CommandMessage(CommandsEnum command, String villageName) {
        this.command = command;
        this.villageName = villageName;
        this.attackRequests = null;
    }

    public CommandMessage(CommandsEnum command, String villageName, List<AttackRequest> attackRequests) {
        this.command = command;
        this.villageName = villageName;
        this.attackRequests = attackRequests;
    }
}
