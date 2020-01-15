package com.example.myframe.controller;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Demo {


    public static void getMovie(String[] args) throws IOException {
        WebClient webClient=new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(10000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        HtmlPage htmlPage=webClient.getPage("https://v.qq.com");
        //获取搜索框
        HtmlTextInput input=(HtmlTextInput)htmlPage.getElementById("keywords");
        //设置搜索框的值
        input.setValueAttribute("斗罗大陆");
        //获取按钮
        HtmlButton button=(HtmlButton)htmlPage.getElementsByTagName("button").get(0);
        //获取点击后的网页代码
        HtmlPage result=button.click();
        HtmlElement as=result.getAnchorByText("...");
        result=as.click();
        Document document = Jsoup.parse(result.asXml());

        Element div=document.getElementsByClass("_playlist").get(0);
        Elements a=div.select("a");
        for(int i=0;i<a.size()-2;i++){
            if(!a.get(i).text().contains("...")){
                System.out.println(a.get(i).text()+"--"+a.get(i).attr("href"));
            }

        }

    }
}
