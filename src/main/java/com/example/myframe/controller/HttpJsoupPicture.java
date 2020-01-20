package com.example.myframe.controller;

import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import com.example.myframe.entity.PictureBean;
import com.example.myframe.service.PictureService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/picture")
public class HttpJsoupPicture {

    @Autowired
    private PictureService pictureService;

    private static Connection connection;
    private static Connection.Response response;

    @RequestMapping(value="/get")
    public RestResponse get(@RequestParam(value="searchValue") String searchValue) throws Exception{
        List<PictureBean> list=new ArrayList<>();
        //先去数据查询有没有符合的数据
        list=pictureService.get(searchValue);
        if(list.size()==0){
            list=this.add(searchValue);
        }
        return new RestResponse(ResultEnum.SUCCESS,list);
    }


    /**
     * 添加到数据库
     * @param searchValue
     * @return
     * @throws Exception
     */
    public List<PictureBean> add(String searchValue) throws Exception{
          List<PictureBean> list=new ArrayList<>();
          Document document=getPage("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&fm=index&pos=history&word="+searchValue);
          String html=document.body().toString();
          //获取所有的图片链接对象字符串
          String imgData=html.substring(html.indexOf("imgData"),html.indexOf("fcadData"));
          //分割每个对象
          String[] data=imgData.split("},");
          for(int i=0;i<data.length;i++){
              String object=data[i];
              //判断是否有图片链接
              if(object.contains("thumbURL")) {
                  PictureBean pictureBean=new PictureBean();
                  String thumbUrl="";
                  if(object.contains("replaceUrl")){
                      thumbUrl = object.substring(object.indexOf("thumbURL") + 11, object.indexOf("replaceUrl") - 3);
                  }else{
                      thumbUrl = object.substring(object.indexOf("thumbURL") + 11, object.indexOf("adType") - 3);
                  }
                  pictureBean.setName(searchValue);
                  pictureBean.setImg(thumbUrl);
                  list.add(pictureBean);
              }

          }
          pictureService.addList(list);
          return list;
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
