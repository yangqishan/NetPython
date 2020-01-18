package com.example.myframe.controller;

import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import com.example.myframe.entity.DetailsBean;
import com.example.myframe.entity.MovieEntity;
import com.example.myframe.service.DetailsService;
import com.example.myframe.service.MovieService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(value="movie")
public class HttpClientMovie {
    @Autowired
    private MovieService movieService;
    @Autowired
    private DetailsService detailsService;

    private static Connection connection;
    private static Connection.Response response;
    /**
     * 获取电影下载资源
     * @param value
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get")
    public RestResponse get(@RequestParam(value="value") String value) throws Exception {
        List<MovieEntity> list=new ArrayList<MovieEntity>();
        HashMap<String,Object> map=new HashMap();
        //先去详情表查询有没有符合的数据
        DetailsBean detailsBean=detailsService.get(value);
        //没有符合的数据开始去网站爬取
        if(detailsBean==null){
            map=this.add(value,map,list);
        }else{
            //去数据库获取所有的下载链接
            list.clear();
            list=movieService.get(value);
            map.put("details",detailsBean);
            map.put("list",list);
        }
        return new RestResponse(ResultEnum.SUCCESS,map);
    }

    /**
     * 网站爬起数据并存到数据库
     * @param values
     * @param map
     * @param list
     * @return
     * @throws Exception
     */
    public  HashMap<String,Object> add(String values,HashMap map,List<MovieEntity> list) throws Exception {
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
        DetailsBean detailsBean=new DetailsBean();
        //获取电影的图片
        String img=document.getElementById("text").select("p").get(0).select("img").attr("src");
        detailsBean.setName(values);
        detailsBean.setImg(img);
        //获取电影的详情内容
        String content=document.getElementById("text").select("p").get(0).text();
        content+=document.getElementById("text").select("p").get(2).text();
        detailsBean.setContent(content);
        detailsBean.setType("movie");
        //增加详情到数据库
        detailsService.add(detailsBean);
        //根据class获取到 页面的 元素内容
        Elements tables = document.getElementsByTag("table");
        //根据td标签来划分
        Elements td = tables.select("td");
        //获取id
        detailsBean=detailsService.get(values);
        int detailsId=detailsBean.getId();
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
                movieEntity.setDetailsId(detailsId);
                list.add(movieEntity);
            }
        }
        //增加整个集合到数据库
        movieService.addList(list);
        map.put("details",detailsBean);
        map.put("list",list);
        return map;
    }

    /**
     * 解析页面dom
     * @param url
     * @return
     * @throws Exception
     */
    public static Document getPage(String url) throws Exception{
        connection=Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
        response = connection.ignoreContentType(true).method(Connection.Method.GET).execute();
        Document document=Jsoup.parse(response.body());
        return document;
    }
}
