package com.example.myframe.controller;


import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.response.RestResponse;
import com.example.myframe.entity.CareerBean;
import com.example.myframe.entity.MovieEntity;
import com.example.myframe.util.JsonUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/career")
public class HttpJsoupCareer {

    private static Connection connection;
    private static Connection.Response response;

    /**
     * 获取行业薪资排行版
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/get")
    public RestResponse get()throws Exception{
        HashMap<String,Object> map=new HashMap<>();
        //获取https://www.kanzhun.com/xs/ 页面的工资排行榜
        Document document=getPage("https://www.kanzhun.com/xs/");
        //得到所有的排行信息
        Elements rem_hot_s=document.getElementsByClass("rem_hot_s").select("dl");
        for(int i=0;i<rem_hot_s.size();i++){
            List<CareerBean> list=new ArrayList<>();
            //获取每个排行的li元素
            Elements li=rem_hot_s.get(i).select("li");
            for(int j=0;j<li.size();j++){
                CareerBean careerBean=new CareerBean();
                String name=li.get(j).select("i").text();//排行
                String title=li.get(j).select("a").text();//地区
                String href=li.get(j).select("a").attr("href");
                String salary=li.get(j).select("span").text();//工资
                careerBean.setName(name);
                careerBean.setTitle(title);
                careerBean.setHref("https://www.kanzhun.com"+href);
                careerBean.setSalary(salary);
                list.add(careerBean);
            }
            map.put("hot"+i,list);
        }
        return new RestResponse(ResultEnum.SUCCESS,map);
    }

    /**
     *  List<MovieEntity> list1= new ArrayList<MovieEntity>();
     *  list1=JsonUtil.decode(jsonArray.toString(), new TypeReference<List<MovieEntity>>() {});
     * @param searchValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getCareer")
    public RestResponse getCareer(@RequestParam(value="searchValue") String searchValue) throws Exception{
        //获取根据搜索职位和公司的查询的数据
        Document document=getPage("https://www.kanzhun.com/search/salary.json?query="+searchValue+"&type=0&cityCode=0&industryCodes=0&pageNum=1&limit=15");
        //截取薪资数据
        String[] data=document.body().toString().split("},");
        List<CareerBean> list=new ArrayList<>();
        for(int i=0;i<data.length-2;i++){
            CareerBean careerBean=new CareerBean();
            String ss=data[i];
            //职位
            careerBean.setTitle(ss.substring(ss.indexOf("positionName")+15,ss.indexOf("count")-3));
            //薪资
            careerBean.setSalary(ss.substring(ss.indexOf("avg")+5,ss.indexOf("min")-2));
            //公司
            /*if(ss.contains("companyName")){
                careerBean.setCompany(ss.substring(ss.indexOf("companyName"),ss.indexOf("companyLogo")));
            }*/
            //图片
            careerBean.setImg(ss.substring(ss.indexOf("companyLogo")+14,ss.indexOf("id")-3));
            //最低
            careerBean.setMin(ss.substring(ss.indexOf("min")+5,ss.indexOf("max")-2));
            //最高
            careerBean.setMax(ss.substring(ss.indexOf("max")+5,ss.indexOf("block")-2));
            list.add(careerBean);
        }
        return new RestResponse(ResultEnum.SUCCESS,list);
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
