$(function(){
    $(".table tr .timezone").each(function(i,ele){


           // var btn_reset = $("#resetCron");
             var btn_reset =  $(ele).find("#resetCron");
            var code = btn_reset.attr("code");
            var sid = btn_reset.attr("sid");
            btn_reset.click(function(){
                var confirm = window.confirm("确定修改任务表达式？");
                if(! confirm) return ;

                var url = ctx + "/scheduler/cron/modify";
                var data = {code:code,id:sid};
                btn_reset.parents(".timezone-box").find("input").each(function(i,ele){
                    data[ele.name] = ele.value;
                });
                $.post(url,data,function(data){
                    if(data.succ){
                        alert("更新成功");
                    }else{
                        alert(data.msg);
                    }
                },"json");
            });
    });

});
function stop(ele){
     var sid =  $(ele).attr("sid");
     var  status =  $(ele).attr("status");
     var statusMapping = {0:1,1:0};
    $.post( ctx + "/scheduler/disabled"  ,{id:sid,status:statusMapping[status]},function(data){
        if(data.succ){
            alert("更新成功");
            window.location.reload();
        }else{
            alert(data.msg);
        }
    },"json");
}
