package com.example.myframe.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DemoController {
    /**
     * @RequestParam(value="current",defaultValue ="1") Integer current,
     * @RequestParam(value="size",defaultValue = "5") Integer size,
     */
    private static Connection connection;
    private static Connection.Response response;

   /* public static void main(String[] arg) throws Exception{
        Document document=getPage("http://piaofang.maoyan.com/second-box");
        //Elements table=document.getElementsByClass("dashboard-table");
        System.out.println(document);
    }*/
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
