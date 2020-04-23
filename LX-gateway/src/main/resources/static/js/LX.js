var  myMap={1:"播放记录",2:"我的点赞",3:"我的收藏"},typeMap= new Map(),countryMap=new Map(),categoryMap= new Map(), user, nowTepy=null, nowCountry=null, nowCategory=null;
var authUrl = "http://localhost:10200",
    gatewayUrl = "http://localhost:10020",
    uploadUrl = "http://localhost:10110",
    videoUrl = "http://localhost:10130",
    userUrl = "http://localhost:10140";
$(function () {
    /* 根据是否登陆显示不同的部分 */
    show();
    init();
    //控制回到顶部模块
    //toTop();
});
/* 根据是否登陆显示不同的部分 */
function show() {
    user = ajaxAndReturnRes(gatewayUrl+'/api/auth/user/me',null,'get',true);
    console.log(user);
    if (user == null) {
        $("#show").text("");
        $("#show").append("<li class=\"layui-nav-item\"> <a href='"+gatewayUrl+"/html/demo-signIn.html' style=\"font-size: large\"> <i class=\"layui-icon layui-icon-ok \" style=\"font-size: 22px; color: #1E9FFF;\"> </i>登陆 </a> </li> <li class=\"layui-nav-item\"> <a href='"+gatewayUrl+"/html/demo-register.html' style=\"font-size: large\"> <i class=\"layui-icon layui-icon-add-1 \" style=\"font-size: 20px; color: #1E9FFF;\"> </i>注册 </a> </li>");
    } else {
        $("#show").text("");
        $("#show").append("<li class=\"layui-nav-item\" lay-unselect=\"\"> <a style=\"font-size: large\"> <i class=\"layui-icon layui-icon-user \" style=\"font-size: 20px; color: #1E9FFF;\"> </i>我的 </a> <dl class=\"layui-nav-child\"> <dd><a href='"+gatewayUrl+"/html/new.html'>我的上传</a></dd> <dd><a href='"+gatewayUrl+"/html/my.html?my=3'>我的收藏</a></dd> <dd><a href='"+gatewayUrl+"/html/my.html?my=2'>我的点赞</a></dd> <dd><a href='"+gatewayUrl+"/html/my.html?my=1'>播放记录</a></dd> <dd><a href='"+authUrl+"/logout\'>退了</a></dd> </dl> </li>");
    }
}
function init() {
    /*        ajax('/api/assort/types',null,'get',true,function (res){
        res.forEach(function (item, index) {
            console.log("--------------"+item.id);
            typeMap[item.id] = item.name;
            console.log("-------type"+typeMap);
        });
        console.log("c:" + c++);
    });
    ajax('/api/assort/categories',null,'get',true,function (res){
        res.forEach(function (item, index) {
            console.log("--------------"+item.id);
            categoryMap[item.id] = item.name;
            console.log("-------category"+typeMap);
        });
    });
    ajax('/api/assort/countries',null,'get',true,function (res){
        res.forEach(function (item, index) {
            console.log("--------------"+item.id);
            countryMap[item.id] = item.name;
            console.log("-------country"+typeMap);
        });
    });*/

    ajaxAndReturnRes(videoUrl + '/assort/types',null,'get',true).forEach(function (item, index) {
        typeMap.set(item.id,item.name);
    });
    ajaxAndReturnRes(videoUrl + '/assort/countries',null,'get',true).forEach(function (item, index) {
        countryMap.set(item.id,item.abbreviation);
    });
    ajaxAndReturnRes(videoUrl + '/assort/categories',null,'get',true).forEach(function (item, index) {
        categoryMap.set(item.id,item.name);
    });
}


/********************
 * 取窗口滚动条高度
 ******************/
function getScrollTop()
{
    var scrollTop=0;
    if(document.documentElement&&document.documentElement.scrollTop)
    {
        scrollTop=document.documentElement.scrollTop;
    }
    else if(document.body)
    {
        scrollTop=document.body.scrollTop;
    }
    return scrollTop;
}
/********************
 * 取窗口可视范围的高度
 *******************/
function getClientHeight()
{
    var clientHeight=0;
    if(document.body.clientHeight&&document.documentElement.clientHeight)
    {
        var clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
    }
    else
    {
        var clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
    }
    return clientHeight;
}
/********************
 * 取文档内容实际高度
 *******************/
function getScrollHeight()
{
    return Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
}
function test(){
    if (getScrollTop()+getClientHeight()==getScrollHeight()){
        alert("到达底部");
    }else{
        alert("没有到达底部");
    }
}


/* 回到顶部或回到主页*/
function toTop() {
    var timerId=setTimeout(function(){
        if (getScrollTop() > 1000) {
            //....
        }
    },0.5);
}

function ajax(url,data,type,async,cache,contentType, sfunc, sprama, efunc, eprama) {
    $.ajax({
        url: url //实际使用请改成服务端真实接口
        ,data: data
        ,type: type
        ,cache: cache
        //,dataType: 'jsonp'
        ,dataType: 'json'
        ,headers: {
            "Access-Control-Allow-Headers": "x-requested-with,access-control-allow-origin",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "*"
        }
        ,contentType: contentType
        //,contentType:"application/json:charset=UTF-8"
        ,async: async   //如果同步为false
        ,success: function(res){
            if (sfunc !== null)
                sfunc(res,sprama);
        }
        ,error:function(XMLHttpRequest) {
            if (efunc !== null)
                efunc(XMLHttpRequest, eprama);
        }
    });
}

/**
 * 此方法为非异步 返回成功后的结果集，如果请求失败，放回null
 * @param url
 * @param data
 * @param type
 * @returns {*}
 */

function ajaxAndReturnRes(url,data,type,cache) {
    var ajaxRes = null;
    $.ajax({
        url: url //实际使用请改成服务端真实接口
        ,data: data
        ,type: type
        ,cache: cache
        ,dataType: 'json'
        //,dataType: 'jsonp'
        ,headers: {
            "Access-Control-Allow-Headers": "x-requested-with,redirect,content-type,access-control-allow-origin",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "*"
        }
        ,contentType:"application/x-www-form-urlencoded:charset=UTF-8"
        //,contentType:"application/json:charset=UTF-8"
        ,async: false   //如果同步为false
        ,success: function(res){
            ajaxRes = res;
        }
        ,error:function(XMLHttpRequest) {
            console.log(XMLHttpRequest);
        }
    });
    return ajaxRes;
}

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
};