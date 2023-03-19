package cc.wybxc.client;

import cc.wybxc.server.Server;
import org.jetbrains.annotations.NotNull;

public final class Client {
    private final Server server;
    private final String token;


    private Client(Server server, String token) {
        this.server = server;
        this.token = token;
    }

    /**
     * 登录
     *
     * @param server   服务器
     * @param uin      账号
     * @param password 密码
     * @return 客户端
     * @throws Server.ServerError 服务器错误
     */
    public static @NotNull Client login(@NotNull Server server, int uin, @NotNull String password) throws Server.ServerError {
        var token = server.passwordLogin(uin, password);
        return new Client(server, token);
    }

    /**
     * 设置事件处理器
     *
     * @param eventHandler 事件处理器
     */
    public void setEventHandler(EventHandler eventHandler) {
        server.setEventHandler((token, event, data) -> {
            if (token.equals(this.token)) {
                switch (event) {
                    case "friendMessage":
                        var friend = new Friend(this, Integer.parseInt(data.substring(0, data.indexOf(' '))));
                        var message = data.substring(data.indexOf(' ') + 1);
                        eventHandler.onFriendMessage(this, friend, message);
                        break;
                    default:
                        System.err.println("Unknown event: " + event);
                }
            }
        });
    }

    /**
     * 获取昵称
     *
     * @return 昵称
     * @throws Server.ServerError 服务器错误
     */
    public String getNickname() throws Server.ServerError {
        return server.getNickname(token);
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     * @throws Server.ServerError 服务器错误
     */
    public void setNickname(String nickname) throws Server.ServerError {
        server.setNickname(token, nickname);
    }

    /**
     * 发送好友消息
     *
     * @param friend  好友
     * @param message 消息
     * @throws Server.ServerError 服务器错误
     */
    public void sendFriendMessage(Friend friend, String message) throws Server.ServerError {
        server.sendFriendMessage(token, friend.uin, message);
    }
}
