package com.zhonggg.spider.service;

import com.zhonggg.spider.dao.SpiderDao;
import com.zhonggg.spider.model.Poem;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            List<String> urls = getUrlsFromIndex();
            // 获取url下的诗文
            List<Poem> poems = getPoemFromUrls(urls);
            // 出入数据库
            spiderDao.addPoemBatch(poems);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private List<Poem> getPoemFromUrls(List<String> urls) throws Exception {
        List<Poem> poems = new ArrayList<>();
        Poem poem ;
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
            poem.setCategory("唐诗三百首");
            //把poem封装到list中
            poems.add(poem);


        }
        return poems;
    }

    private static List<String> getUrlsFromIndex() throws IOException {
        //创建一个List
        List<String> urls = new ArrayList<>();
        String url = "https://so.gushiwen.org/gushi/songsan.aspx";
        //创建连接
        Connection connect = Jsoup.connect(url);
        //设置参数
        connect.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
        connect.timeout(5000);
        //爬取数据
        Document document = connect.get();
        Elements elements = document.select("div.typecont a");
        for (Element element : elements) {
            System.out.println();
            urls.add("https://so.gushiwen.org"+element.attr("href"));
        }
        return urls;
    }
}
