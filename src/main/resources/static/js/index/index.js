executeAjax("/xiaochengxu/getNews","POST",{pageSize:50,pageNo:1},function (resp) {
   if(resp.code == 0){
       console.log(resp.data.data);
       var html = ""
       resp.data.data.forEach(function(tmp){
           console.log(tmp.title)
           html += "<li class=\"list-group-item\">  "
           html += "<a href=\"" +
               tmp.url+
               "\">"
           html += tmp.title
           html += "</a>"
           html += "<div class='news_date'>"
           html += tmp.date
           html + "</div>"
           html += "</li>"
       });
       console.log(html)
      $("#list").html(html)
   }
})