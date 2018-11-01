package em;

import redis.clients.jedis.JedisPubSub;

/**
 * Subscriber class
 *
 * @author Administrator
 * @date
 */
public class Subscriber extends JedisPubSub {
    public Subscriber(){}

    /**
     * 从channel接受到消息后要做的事
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
        super.onMessage(channel, message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d", channel, subscribedChannels));
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d", channel, subscribedChannels));
        super.onUnsubscribe(channel, subscribedChannels);
    }
}
