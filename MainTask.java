package cn.itcast.crawer;
import org.jsoup.Jsoup;
import  org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MainTask implements PageProcessor {
    @Override
    public void process(Page page) {
        Document doc = Jsoup.parse(page.getHtml().toString());
        if(page.getUrl().toString().matches("https://ncov.dxy.cn/ncovh5/view/pneumonia")){
        Elements text= doc.getElementsByAttributeValueContaining("id","getListByCountryTypeService2true") ;//ElementsByAttributeStarting("getListByCountryTypeService2true");
        System.out.print(text);

        }
    }
    public double getNum(String string)
    {
        String s=string.replaceAll("[\\u4e00-\\u9fa5]|[/m?]","")
                .trim();
        double i =Double.valueOf(s).doubleValue();
        return i;
    }
    @Override
    public Site getSite(){
        Site site =Site.me()
                .setCharset("utf8")
                .setTimeOut(30000)
                .setRetrySleepTime(10000)
                .setRetryTimes(100)
                .setSleepTime(20000);

        return site;
    }
    public void textMatchClass(Elements a){
        String b=a.toString();
        char[] c=b.toCharArray();
        int flag;
        for(int i=0;i<b.length();i++){
        }
    }
    public String check(char[] a,int n,int m,String b)
    {
       char[] c=b.toCharArray();
       boolean flag=true;
        for(int i=0;n<m&&i<b.length();n++,i++)
        {
            if(a[n]==c[i])
                flag=true;
            else
                flag=false;
        }
        if(flag)
        {
            String d=null;
           while(a[n]!=',')
           {
               d+=a[n];
               n++;
           }
           return d;
        }
        else
            return null;

    }
}
