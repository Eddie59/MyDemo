package cn.adapter;


/**
 * AudioPlayer class
 *
 * @author Administrator
 * @date
 */
public class AudioPlayer implements MediaPlayer {

    /**
     * 适配器类
     */
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        //老款播放器可以直接播放mp3格式
        if(audioType.equalsIgnoreCase("mp3"))
        {
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //如果是vlc mp4格式，老款已经播放不了，交给适配器，适配器去找新款播放器
        else if(audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4"))
        {
            mediaAdapter=new MediaAdapter(audioType);
            mediaAdapter.play(audioType,fileName);
        }
        else
        {
            System.out.println("Invalid media. "+ audioType + " format not supported");
        }

    }
}
