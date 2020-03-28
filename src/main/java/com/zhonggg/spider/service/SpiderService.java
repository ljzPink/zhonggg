package com.zhonggg.spider.service;

import com.zhonggg.commonUtils.DateUtil;
import com.zhonggg.spider.dao.SpiderDao;
import com.zhonggg.spider.model.NewsInfo;
import com.zhonggg.spider.model.Poem;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-05 14:33
 */
@Service
public class SpiderService {

    @Autowired
    private SpiderDao spiderDao;
    public void spiderPoem() {
        try {
            //获取url列表
            Map<String, List<String>> urlsFromIndex = getUrlsFromIndex();
            // 获取url下的诗文
            List<Poem> poems = getPoemFromUrls(urlsFromIndex);
            // 出入数据库
            spiderDao.addPoemBatch(poems);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private List<Poem> getPoemFromUrls(Map<String, List<String>> urlMap) throws Exception {
        List<Poem> poems = new ArrayList<>();
        Poem poem ;
        for (Map.Entry<String, List<String>> m : urlMap.entrySet()) {
            String category = m.getKey();
            List<String> urls = m.getValue();
            for (String url : urls) {
                //创建连接
                Document document = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36")
                        .timeout(5000).get();
                //诗词整体内容
                Element element = document.select("div.sons div.cont").first();
                //诗词标题
                String title = element.select("h1").first().text();
                //获取朝代和作者
                String dyAndAuthor = element.select("p.source a").text();
                String dynasty = dyAndAuthor.split(" ")[0];
                String author = dyAndAuthor.split(" ")[1];
                //获取诗词内容
                String content = element.select("div.contson").text();

                poem = new Poem();
                poem.setTitle(title);
                poem.setDynasty(dynasty);
                poem.setAuthor(author);
                poem.setContent(content);
                poem.setCategory(category);
                //把poem封装到list
                poems.add(poem);


            }
        }

        return poems;
    }

    private static Map<String ,List<String>> getUrlsFromIndex() throws IOException {

        //创建一个MAP
        HashMap<String, List<String>> stringListHashMap = new HashMap<>();
        String url = "https://so.gushiwen.org/gushi/songsan.aspx";
        //创建连接
        Connection connect = Jsoup.connect(url);
        //设置参数
        connect.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
        connect.timeout(5000);
        //爬取数据
        Document document = connect.get();
        Elements elements = document.select("div.typecont");
        for (Element element : elements) {
            String category = element.select(".bookMl strong").first().text();
            List<String> urls = new ArrayList<>();
            stringListHashMap.put(category,urls);
            Elements a = element.select("a");
            for (Element el: a) {
                urls.add("https://so.gushiwen.org"+el.attr("href"));
            }
        }
        return stringListHashMap;
    }

    public void chnDic() {

    }

    public void news() {
        try {

            // 获取url下的诗文
            List<NewsInfo> poems = getNewsInfos("http://www.xinhuanet.com/whxw.htm");
            // 出入数据库
            spiderDao.addNewsInfoBatch(poems);
        }catch (Exception e){

        }

    }

    private List<NewsInfo> getNewsInfos(String url) throws Exception {
        List<NewsInfo> NewsInfos = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        connect.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
        connect.timeout(5000);
        Document document = connect.get();
        Elements elements = document.select(".dataList .clearfix");
        for (Element element : elements) {
            NewsInfo newsInfo = new NewsInfo();
            NewsInfos.add(newsInfo);
            newsInfo.setSource("新华社新闻");
            newsInfo.setUpdateTime(DateUtil.format(new Date()));
            Element a = element.select("h3 a").first();
            newsInfo.setUrl(a.attr("href"));
            newsInfo.setTitle(a.text());
            newsInfo.setDate(element.select("span").first().text());
        }
        return NewsInfos;
    }
}
