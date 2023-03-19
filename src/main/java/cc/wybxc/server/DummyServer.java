package cc.wybxc.server;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyServer implements Server {
    List<String> clients;
    Map<String, String> nicknames;

    ServerEventHandler eventHandler;

    public DummyServer() {
        clients = new ArrayList<>();
        nicknames = new HashMap<>();
    }

    @Override
    public String passwordLogin(int uin, @NotNull String password) throws ServerError {
        var token = clients.size() + "";
        if (uin == 123456 && password.equals("123456")) {
            clients.add(token);
            nicknames.put(token, "Dummy");
            return token;
        }
        throw new ServerError("Wrong password");
    }

    @Override
    public void setEventHandler(ServerEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public String getNickname(String token) throws ServerError {
        if (clients.contains(token)) {
            return nicknames.get(token);
        }
        throw new ServerError("Wrong token");
    }

    @Override
    public void setNickname(String token, String nickname) throws ServerError {
        if (clients.contains(token)) {
            nicknames.put(token, nickname);
            return;
        }
        throw new ServerError("Wrong token");
    }

    @Override
    public void sendFriendMessage(String token, int uin, String message) throws ServerError {
        if (clients.contains(token)) {
            System.out.println("[Server] " + nicknames.get(token) + " -> " + uin + ": " + message);
            eventHandler.onEvent(token, "friendMessage", uin + " " + "echo: " + message);
            return;
        }
        throw new ServerError("Wrong token");
    }
}
