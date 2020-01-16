package com.example.myframe.controller;


import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/goods")
public class HttpJsoupGoods {

    private static Connection connection;
    private static Connection.Response response;
    @RequestMapping(value="/search")
    public RestResponse find(@RequestParam(value="searchValue") String searchValue,
                                                 HttpServletRequest request) throws Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        String con=searchValue;
        searchValue=searchValue.replace(":","%3A");
        searchValue=searchValue.replace("/","%2F");
        searchValue=searchValue.replace("?","%3F");
        searchValue=searchValue.replace("=","%3D");
        searchValue=searchValue.replace("&","%26");
        searchValue=searchValue.replace(" ","_");
        //冒号 %3A   / %2F  问好 %3F 等于号 %3D & %26 空格 _
        if(!(con.contains("detail.tmall")||con.contains("jd"))){
            map.put("msg","网址有误重新输入");
            map.put("href","null");
            return new RestResponse(ResultEnum.SUCCESS,map);
        }
        String url="http://www.hisprice.cn/his.php?hisurl="+searchValue;
        //获取checkCodeId
        Document document=getPage(url);
        String checkCode=document.getElementById("checkCodeId").val();
        //获取code码
        Document documentCode=getCode(checkCode,con);
        String body= documentCode.toString();
        String code=body.substring(body.indexOf("code\":\"")+7,body.indexOf("\",\"taoInfoUrl\""));
        //获取历史价格数据
        String url1="http://212.64.43.245/vv/dm/historynew.php?code="+code;
        //获取是否有优惠劵
        String url2="http://212.64.43.245/vv/dm/coupon.php?code="+code;
        //标题和优惠券链接
        Document document2=getPage(url2);
        body=document2.toString();
        String[] ss=body.split("\",");
        //获取title
        String title=ss[1].substring(ss[1].indexOf("title")+8);
        //获取优惠券链接
        String urls="";
           /* if(ss[2].contains("isTaoke")){
              String id=ss[2].substring(ss[2].indexOf("aid"),ss[2].indexOf("}"));
              id=id.replace("aid\":\"","");
              id=id.replace("\"","");
                urls="http://212.64.43.245/vv/dm/couponClick.php?aid="+id;
                map.put("href",urls);
                //获取淘宝优惠券链接
               //connection=Jsoup.connect(urls);
                connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
                response = connection.followRedirects(true).ignoreContentType(true).method(Connection.Method.GET).execute();
                String l=response.url().toString();
            }else{
                map.put("href","null");
            }*/

        //拿到历史价格
        Document document1=getPage(url1);
        body= document1.toString();
        //分割字符串 取价格个和时间
        String[] datas=body.substring(body.indexOf("chart(")+6,body.indexOf(", '")).split("],");
        List<Goods> list=new ArrayList<Goods>();
        //定义最大最下值
        Float min = null;
        Float max=null;
        //开始循环每次添加一个对象
        for(int i=0;i<datas.length-1;i++){
            Goods goods=new Goods();
            String times=datas[i].substring(datas[i].indexOf("C(")+2,datas[i].indexOf("),")).replace(",","-");
            String price=datas[i].substring(datas[i].indexOf("),")+2);
            if(i==0){
                min=Float.parseFloat(price);
                max=Float.parseFloat(price);
            }else{
                if(Float.parseFloat(price)<=min){
                    min=Float.parseFloat(price);
                }
                if(Float.parseFloat(price)>=max){
                    max=Float.parseFloat(price);
                }
            }
            goods.setPrice(price);
            goods.setTimes(times);
            list.add(goods);
            //request.setAttribute("time"+k,datas[i].substring(datas[i].indexOf("C(")+2,datas[i].indexOf("),")).replace(",","-"));
            //request.setAttribute("data"+k,datas[i].substring(datas[i].indexOf("),")+2));
        }
        map.put("msg","正确");
        map.put("min",min);
        map.put("max",max);
        map.put("title",title);
        map.put("list",list);
        return new RestResponse(ResultEnum.SUCCESS,map);
    }

    public static Document getPage(String url) throws Exception {
        connection=Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
        response = connection.ignoreContentType(true).method(Connection.Method.GET).execute();
        Document document=Jsoup.parse(response.body());
        return document;
    }

    public static Document  getCode(String checkCode,String searchValue)throws Exception {
        connection=Jsoup.connect("http://www.hisprice.cn/dm/ptinfo.php");
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
        connection.data("checkCode",checkCode);
        connection.data("con",searchValue);
        response = connection.ignoreContentType(true).method(Connection.Method.POST).execute();
        Document document=Jsoup.parse(response.body());
        return document;
    }

    //定义一个商品类
    public class Goods{
        private String price;
        private String times;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "price='" + price + '\'' +
                    ", times='" + times + '\'' +
                    '}';
        }
    }
}
