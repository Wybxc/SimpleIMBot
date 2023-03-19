package cc.wybxc.server;

public interface ServerEventHandler {
    void onEvent(String token, String event, String data);
}
