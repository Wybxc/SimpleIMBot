package cc.wybxc;

import cc.wybxc.client.Client;
import cc.wybxc.client.EventHandler;
import cc.wybxc.client.Friend;
import cc.wybxc.server.DummyServer;
import cc.wybxc.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new DummyServer();
        try {
            Client client = Client.login(server, 123456, "123456");
            client.setEventHandler(new EventHandler() {
                @Override
                public void onFriendMessage(Client client, Friend event, String message) {
                    System.out.println("[Client] Friend " + event.uin + " said: " + message);
                }
            });

            client.setNickname("nickname");
            System.out.println("[Client] Nickname: " + client.getNickname());

            Friend friend = new Friend(client, 654321);
            friend.sendMessage("Hello!");
        } catch (Server.ServerError err) {
            System.err.println("Server error: " + err.getMessage());
        }
    }
}
