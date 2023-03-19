package cc.wybxc.client;

public abstract class EventHandler {
    public abstract void onFriendMessage(Client client, Friend event, String message);
}
