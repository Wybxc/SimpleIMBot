package cc.wybxc.client;

import cc.wybxc.server.Server;

public class Friend {
    private final Client client;
    public final int uin;

    /**
     * 好友
     *
     * @param client 客户端
     * @param uin    好友账号
     */
    public Friend(Client client, int uin) {
        this.client = client;
        this.uin = uin;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     * @throws Server.ServerError 服务器错误
     */
    public void sendMessage(String message) throws Server.ServerError {
        client.sendFriendMessage(this, message);
    }
}
