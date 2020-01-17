package com.example.myframe.controller;

import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import com.example.myframe.entity.DetailsBean;
import com.example.myframe.entity.StarBean;
import com.example.myframe.service.DetailsService;
import com.example.myframe.service.StarService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RequestMapping(value="/star")
@RestController
public class HttpJsoupStar {

    @Autowired
    private DetailsService detailsService;
    @Autowired
    private StarService starService;

    private static Connection connection;
    private static Connection.Response response;

    @RequestMapping(value="/get")
    public RestResponse get(@RequestParam(value="searchValue") String searchValue) throws Exception{
        //去数据库查询有没有这个明星
        DetailsBean detailsBean=detailsService.get(searchValue);
        HashMap<String,Object> map=new HashMap<>();
        List<StarBean> list=new ArrayList<>();
        if(detailsBean==null){
            //添加到数据库
          map=this.add(searchValue,map,list);
        }else{
            //去数据库查询符合的信息
            list=starService.get(searchValue);
            map.put("details",detailsBean);
            map.put("list",list);
        }
        return new RestResponse(ResultEnum.SUCCESS,map);
    }

    //加到数据库
    public HashMap<String,Object> add(String value, HashMap<String,Object> map, List<StarBean> list) throws Exception{
        Document document=getPage("https://baike.baidu.com/item/"+value);
        DetailsBean detailsBean=new DetailsBean();
        //明星照片
        Elements imgs=document.getElementsByClass("lemmaWgt-secondsKnow-gallery-li");
        String img=imgs.get(1).select("img").attr("src");
        detailsBean.setName(value);
        detailsBean.setImg(img);
        //基本信息
        Elements basic=document.getElementsByClass("lemma-summary");
        detailsBean.setContent(basic.text());
        detailsBean.setType("star");
        //把基本信息存到数据
         detailsService.add(detailsBean);
        //人物关系
        Elements relations=document.getElementById("slider_relations").select("li");
        detailsBean=detailsService.get(value);
        int id=detailsBean.getId();
        //爬取明星的人际关系
        for(int i=0;i<relations.size();i++){
            StarBean starBean=new StarBean();
            Element li=relations.get(i);
            starBean.setName(value);
            //获取明星的照片
            starBean.setTitle(li.select("img").attr("src"));
            //获取明星的姓名
            starBean.setContent(li.text());
            starBean.setDetailsId(id);
            starBean.setType("relations");
            list.add(starBean);
        }
        //详细信息
        Elements details=document.getElementsByClass("basic-info cmn-clearfix");
        for(int j=0;j<details.select("dt").size();j++){
            StarBean starBean=new StarBean();
            Element dt=details.select("dt").get(j);
            Element dd=details.select("dd").get(j);
            starBean.setName(value);
            //获取标题
            starBean.setTitle(dt.text());
            //获取内容
            starBean.setContent(dd.text());
            starBean.setDetailsId(id);
            starBean.setType("details");
            list.add(starBean);
        }
        //经历
        Elements exp=document.getElementsByClass("para");
        for(int k=0;k<25;k++){
            StarBean starBean=new StarBean();
            starBean.setName(value);
            starBean.setContent(exp.get(k).text());
            starBean.setDetailsId(id);
            starBean.setType("exp");
            list.add(starBean);
        }
        starService.addList(list);
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
        connection= Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
        response = connection.ignoreContentType(true).method(Connection.Method.GET).execute();
        Document document=Jsoup.parse(response.body());
        return document;
    }
}
