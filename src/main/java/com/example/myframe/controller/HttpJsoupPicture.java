package com.example.myframe.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpJsoupPicture {

    private static Connection connection;
    private static Connection.Response response;

    public static void main(String[] args) throws Exception{
        Document document=getPage("http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1579162874562_R&pv=&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&sid=&word=胡歌");
        Element images=document.getElementById("imgid");
        System.out.println(images.html());
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
