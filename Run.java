package cn.itcast.crawer;
import us.codecraft.webmagic.Spider;
public class Run {
    public static void main(String[] args)
    {
        Spider.create(new MainTask())
                .addUrl("https://ncov.dxy.cn/ncovh5/view/pneumonia")
                .thread(5)
                .run();
    }
}
