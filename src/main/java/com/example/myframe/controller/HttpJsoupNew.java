package com.example.myframe.controller;

import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import com.example.myframe.entity.NewBean;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value="new")
@RestController
public class HttpJsoupNew {
    private static Connection connection;
    private static Connection.Response response;

    /**
     * 获取所有新闻
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/get")
    public RestResponse get()throws Exception{
        Document document=getPage("https://news.baidu.com/");
        HashMap<String,Object> map=new HashMap<>();
        List<NewBean> list=new ArrayList<>();
        //获取热点所有的ul下的新闻
        Elements ul=document.getElementById("pane-news").select("ul");
        for(int i=0;i<5;i++){
            Elements li=ul.get(i).select("li");
            for(int j=0;j<li.size();j++){
                NewBean newBean=new NewBean();
                newBean.setTitle(li.get(j).text());
                newBean.setHref(li.get(j).select("a").attr("href"));
                //把每条新闻加入list
                list.add(newBean);
            }
        }
        map.put("panes",list);

        List<NewBean> guonei1=new ArrayList<>();
        //获取国内所有的新闻col_focus
        document=getPage("https://news.baidu.com/guonei");
        Element divs=document.getElementById("col_focus");
        Elements a=divs.getElementsByClass("l-left-col").select("a");
        for(int i=0;i<a.size();i++){
            NewBean newBean=new NewBean();
            newBean.setTitle(a.get(i).text());
            newBean.setHref(a.get(i).attr("href"));
            guonei1.add(newBean);
        }
        map.put("guonei1",guonei1);
        List<NewBean> guonei2=new ArrayList<>();
        //获取国内新闻带有图片的focus-videos
        Elements item=document.getElementById("focus-videos").select("div");
        for(int j=0;j<item.size();j++){
            Elements picture=item.get(j).select("p").get(0).select("img");
            Elements title=item.get(j).select("p").get(1).select("a");
            NewBean newBean=new NewBean();
            newBean.setTitle(title.text());
            newBean.setHref(title.attr("href"));
            newBean.setImg(picture.attr("src"));
            guonei2.add(newBean);
        }
        map.put("guonei2",guonei2);

        List<NewBean> guoji1=new ArrayList<>();
        //获取国际的所有新闻col_focus
        document=getPage("https://news.baidu.com/guoji");
        divs=document.getElementById("col_focus");
        a=divs.getElementsByClass("l-left-col").select("a");
        for(int i=0;i<a.size();i++){
            NewBean newBean=new NewBean();
            newBean.setTitle(a.get(i).text());
            newBean.setHref(a.get(i).attr("href"));
            guoji1.add(newBean);
        }
        map.put("guoji1",guoji1);
        List<NewBean> guoji2=new ArrayList<>();
        //获取国际的新闻带有照片的focus-aside-news
        item=document.getElementById("focus-aside-news").select("li");
        for(int j=0;j<item.size();j++){
            //获取照片和内容
            Elements img=item.get(j).select("a");
            NewBean newBean=new NewBean();
            newBean.setTitle(img.text());
            newBean.setHref(img.attr("href"));
            newBean.setImg(img.select("img").attr("r_src"));
            guoji2.add(newBean);
        }
        map.put("guoji2",guoji2);

        List<NewBean> junshi1=new ArrayList<>();
        //获取军事所有新闻col_focus
        document=getPage("https://news.baidu.com/mil");
        divs=document.getElementById("col_focus");
        a=divs.getElementsByClass("l-left-col").select("a");
        for(int i=0;i<a.size();i++){
            NewBean newBean=new NewBean();
            newBean.setTitle(a.get(i).text());
            newBean.setHref(a.get(i).attr("href"));
            junshi1.add(newBean);
        }
        map.put("junshi1",junshi1);
        List<NewBean> junshi2=new ArrayList<>();
        //获取国际的新闻带有照片的mil-video
        item=document.getElementById("mil-video").select("div");
        for(int j=0;j<item.size();j++){
            Elements picture=item.get(j).select("p").get(0).select("img");
            Elements title=item.get(j).select("p").get(1).select("a");
            NewBean newBean=new NewBean();
            newBean.setTitle(title.text());
            newBean.setHref(title.attr("href"));
            newBean.setImg(picture.attr("src"));
            junshi2.add(newBean);
        }
        map.put("junshi2",junshi2);
        return new RestResponse(ResultEnum.SUCCESS,map);
    }

    /**
     * 获取新闻的详情
     * @param url
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getDetails")
    public RestResponse getDetails(@RequestParam(value="url") String url) throws Exception{
        //解析页面dom
        Document document=getPage(url);
        HashMap<String,Object> map=new HashMap<>();
        List<NewBean> list=new ArrayList<>();
        String title=document.getElementsByClass("article-title").text();
        map.put("title",title);
        //获取新闻的详情
        Elements p=document.getElementById("article").select("p");
        //获取新闻详情的图片
        Elements img=document.getElementById("article").select("img");
        for(int i=0;i<p.size();i++){
            NewBean newBean=new NewBean();
            newBean.setTitle(p.get(i).text());
            if(i<img.size()){
                newBean.setImg(img.get(i).attr("src"));
            }
           list.add(newBean);
        }
        map.put("list",list);
        return new RestResponse(ResultEnum.SUCCESS,map);
    }
    /**
     * 解析页面dom
     * @param url
     * @return
     * @throws Exception
     */
    public static Document getPage(String url) throws Exception{
        connection= Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
        response = connection.ignoreContentType(true).method(Connection.Method.GET).execute();
        Document document=Jsoup.parse(response.body());
        return document;
    }
}
