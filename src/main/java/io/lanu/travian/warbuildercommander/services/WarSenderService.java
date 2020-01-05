package io.lanu.travian.warbuildercommander.services;

import io.lanu.travian.warbuildercommander.models.AttackRequest;
import io.lanu.travian.warbuildercommander.models.CommandMessage;

import java.util.List;

public interface WarSenderService {
    void send(CommandMessage commandMessage);
}
