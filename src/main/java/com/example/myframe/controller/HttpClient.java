package com.example.myframe.controller;

import com.example.myframe.dao.MovieDao;
import com.example.myframe.entity.MovieEntity;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HttpClient {
    @Autowired
    private MovieDao movieDao;

    List<MovieEntity> list=new ArrayList<MovieEntity>();
    private static Connection connection;
    private static Connection.Response response;
    @GetMapping(value = "/find")
    public String find(@RequestParam(value="values") String values,
                     HttpServletRequest request) throws Exception {
     list=movieDao.get(values);
     if(list.size()==0){
         this.add(values);
     }
        request.getSession().setAttribute("list",list);
        return "index";
    }
    public void add(String values) throws Exception {
        // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
        WebClient webclient = new WebClient();
        HtmlPage htmlpage = webclient.getPage("http://www.6vhao.tv");
        final HtmlForm htmlForm=htmlpage.getFormByName("searchform");
        final HtmlTextInput value =  htmlForm.getInputByName("keyboard");
        final HtmlSubmitInput submint=htmlForm.getInputByName("submit");
        //设置搜索数据
        value.setValueAttribute(values);
        final HtmlPage htmlPage1=(HtmlPage)submint.click();
        Document table=Jsoup.parse(htmlPage1.asXml());
        Element  div=table.getElementsByClass("listBox").get(0);
        Element a=div.getElementsByTag("a").get(0);
        //HtmlPage page=a.click();
        String url=a.attr("href");
        //编码格式的转换
        Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);
        //根据class获取到 页面的 元素内容
        Elements tables = document.getElementsByTag("table");
        //根据td标签来划分
        Elements td = tables.select("td");
        for(int j=0;j<td.size();j++){
            //获取到标签中的内容
            MovieEntity movieEntity=new MovieEntity();
            //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址ed2k magnet
            String s = td.get(j).select("a").attr("href");
            if(s.contains("ed2k")||s.contains("magnet")){
                String text = td.get(j).text();
                movieEntity.setName(values);
                movieEntity.setHref(s);
                movieEntity.setContent(text);
                //movieDao.insert(movieEntity);
                list.add(movieEntity);
            }
        }
    }

    public static Document getPage(String url) throws Exception{
        connection=Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
        response = connection.ignoreContentType(true).method(Connection.Method.GET).execute();
        Document document=Jsoup.parse(response.body());
        return document;
    }
}
