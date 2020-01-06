package io.lanu.travian.warbuildercommander.services;

import io.lanu.travian.warbuildercommander.models.CommandMessage;

public interface WarSenderService {
    void send(CommandMessage commandMessage);
}
