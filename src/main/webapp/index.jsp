<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>


<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <link rel="stylesheet" href="./css/x-admin.css" media="all">
    <script src="js/jquery.min.js"></script>

<style>

</style>

</head>
<body>

<el-button type="primary" icon="el-icon-search">搜索</el-button>
<input type="submit" onclick="loading()" value="加载"/>
<table>
    <tr class="layui-table">
        <td><input type="checkbox" id="check"/>全选/取消</td>
        <td >编号</td>
        <td >成绩单序号</td>
        <td >姓名</td>
        <td >科目</td>
        <td >试卷名称</td>
        <td >成绩</td>
        <td >考试时间</td>
        <td >试卷答案</td>
        <td >操作</td>
    </tr>
</table>
<div id="table" style="display: none;">
    <table >


    </table>
</div>
<script type="text/javascript">
   var table=$("#table");
   var current=${current};
   var size=${size};
   function loading() {
       table.empty();
        $.ajax({
            url:"get",
            type:"post",
            dataType:"json",
            data:{
                current:current,
                size:size
            },
            success:function (data) {
             //alert(data[0].achievement
               for(var i=0;i<data.records.length;i++){
                   table.append('<tr class="layui-table" style="cursor:pointer" ondblclick="edit(this)">\n' +
                   '            <td><input type="checkbox" name="num"/></td>\n' +
                   '            <td>'+data.records[i].id+'</td>\n' +
                   '            <td>'+data.records[i].name+'</td>\n' +
                   '            <td>'+data.records[i].subject+'</td>\n' +
                   '            <td>'+data.records[i].title+'</td>\n' +
                   '            <td>'+data.records[i].achievement+'</td>\n' +
                   '            <td>'+data.records[i].addtime+'</td>\n' +
                   '            <td>'+data.records[i].ranswer+'</td>\n' +
                   '            <td><a href="#" onclick="del(this)">删除</a></td>\n' +
                   '        </tr>')
                }
                table.append('<a href="#" onclick="--current;loading();">上一页</a><a href="#" onclick="++current;loading();">下一页</a>')
                table.slideDown();
            }

        });
    }
   function edit(obj) {
       //$(obj).find("td").eq(0).html('<input value="'+$(obj).find("td").eq(0).text()+'">')
        $(obj).find("td").eq(1).html('<input style="width: 20px" value="'+$(obj).find("td").eq(1).text()+'">')
        $(obj).find("td").eq(2).html('<input style="width: 40px" value="'+$(obj).find("td").eq(2).text()+'">')
        $(obj).find("td").eq(3).html('<input style="width: 40px" value="'+$(obj).find("td").eq(3).text()+'">')
        $(obj).find("td").eq(4).html('<input style="width: 120px" value="'+$(obj).find("td").eq(4).text()+'">')
        $(obj).find("td").eq(5).html('<input style="width: 20px" value="'+$(obj).find("td").eq(5).text()+'">')
        $(obj).find("td").eq(6).html('<input style="width: 100px" value="'+$(obj).find("td").eq(6).text()+'">')
        $(obj).find("td").eq(7).html('<input style="width: 120px" value="'+$(obj).find("td").eq(7).text()+'">')
        //$(obj).find("td").eq(8).html('<a href="#" onclick="find(this)">修改</a>')
        $(obj).append('<td><a href="#" onclick="find(this)">修改</a></td>')
    }
   function find(obj) {
       var ele=$(obj).parents("tr").find("input");
        //alert(ele.eq(1).val()+ele.eq(2).val()+ele.eq(3).val()+ele.eq(4).val()+ele.eq(5).val()+ele.eq(6).val()+ele.eq(7).val())
        $(obj).parents("tr").find("td").eq(1).text(ele.eq(1).val())
        $(obj).parents("tr").find("td").eq(2).text(ele.eq(2).val())
        $(obj).parents("tr").find("td").eq(3).text(ele.eq(3).val())
        $(obj).parents("tr").find("td").eq(4).text(ele.eq(4).val())
        $(obj).parents("tr").find("td").eq(5).text(ele.eq(5).val())
        $(obj).parents("tr").find("td").eq(6).text(ele.eq(6).val())
        $(obj).parents("tr").find("td").eq(7).text(ele.eq(7).val())
        $(obj).parents("tr").find("td").eq(9).remove()
   }

   function del(obj) {
       $(obj).parents("tr").remove();
   }
   $("#check").click(function () {
       var flag=$("#check").is(':checked')
       var ch=  $("input[name='num']")
       for(var i=0;i<ch.length;i++){
           if(flag==true){
               ch[i].checked=true
           }else{
               ch[i].checked=false
           }
       }


   })
</script>
</body>
</html>