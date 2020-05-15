const basrUrl = "https://zhonggg.com/";

function executeAjax(url,type, param, callbackFunc){
    let result;
    $.ajax({
        type : type,
        url : basrUrl +url,
        data : JSON.stringify(param),
        xhrFields: { withCredentials:true },
        contentType: "application/json; charset=utf-8",
        dataType : "json",
        success : function(resp) {
            if (resp.result && resp.result === false){


            }
            else{
                if(!!callbackFunc) callbackFunc(resp);
            }
            result = resp.data;
        },
        error : function(jqXHR, textStatus, errorThrown) {
            var msg = '';
            if (jqXHR.status === 0) {
                msg = '与服务器断开,请检查网络';
            } else if (jqXHR.status == 404) {
                msg = '请求没有响应';
            } else if (jqXHR.status == 500) {
                msg = '请求发生异常,请检查日志';
            } else {
                msg = '未定义错误,请查询服务日志';
            }
            console.log(msg);
        }
    });

    return result;
}