package cn.adapter;

import cn.adapter.advanced.AdvancedMediaPlayer;
import cn.adapter.advanced.Mp4Player;
import cn.adapter.advanced.VlcPlayer;

/**
 * MediaAdapter class
 *
 * @author Administrator
 * @date
 */
public class MediaAdapter implements MediaPlayer {

    /**
     * MediaAdapter适配类，打通了MediaPlayer接口和MediaAdapter接口
     */
    AdvancedMediaPlayer advancedMediaPlayer;

    /**
     * @param audioType 音频类型
     *                  根据音频类型来创建对应的播放器
     */
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }

}
