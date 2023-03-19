package cc.wybxc.server;

import org.jetbrains.annotations.NotNull;

public interface Server {
    class ServerError extends Exception {
        public ServerError(String message) {
            super(message);
        }
    }
    String passwordLogin(int uin, @NotNull String password) throws ServerError;

    void setEventHandler(ServerEventHandler eventHandler);

    String getNickname(String token) throws ServerError;

    void setNickname(String token, String nickname) throws ServerError;

    void sendFriendMessage(String token, int uin, String message) throws ServerError;
}
