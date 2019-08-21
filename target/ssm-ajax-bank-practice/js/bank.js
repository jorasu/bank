$(function () {
    var url=location.search;
    var reg=/\d+$/;
    var accNo=url.match(reg)[0];
    $("#accNo").html(accNo);

     /*查询余额*/
     $("#searchBalance").click(function () {
         $("#welcome").css("display","none");
         $.ajax({
             url:"queryBalance.do",
             type:"post",
             data:{"cardNo":accNo},
             async:false,
             dataType:"text",
             success:function (res) {
                $("#content_rignt").html(`  <h2 id="bal_1">您的账户余额是：</h2>
                <h3 id="bal_2">人民币：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${res}元</h3>`);
             },
             error:function () {
                 console.log("queryBalance.do error。。。")
             }
         })
     });

     /*修改密码*/
    $("#updaPwd").click(function () {
        $("#welcome").css("display","none");
        $("#content_rignt").html(` <div id="updatePwd">
                   <h3>当前操作：修改密码。请按要求填写完整后点"修改"按钮</h3>
                   <ul>
                       <li>
                           请输入现在的密码：<input id="oldPwd" name="oldPwd" type="password"/>
                          <span id="erroldPwd"></span>
                       </li>
                       <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           请输入新密码：<input id="newPwd" name="newPwd" type="password"/>
                           <span id="errnewPwd"></span>
                       </li>
                       <li>请再次输入新密码：<input id="reNewPwd" name="reNewPwd" type="password"/>
                           <span id="errreNewPwd"></span>
                       </li>
                       <li>
                           <input type="button" value="修改" id="updPwd"/>
                       </li>
                       
                   </ul>
               </div>`);
    });

    /*定义委托事件  输入现在密码失去焦点事件*/
    $("#content_rignt").on("blur","#oldPwd",function () {
        var pwd=$("#oldPwd").val();
        $.ajax({
            url:"login.do",
            type:"post",
            data:{"cardNo":accNo,"pwd":pwd},
            async:false,
            dataType:"text",
            success:function (mes) {
                if(mes.trim()=="loginOk"){
                    $("#erroldPwd").html("密码正确").css("color","green");
                }else {
                    $("#erroldPwd").html(mes).css("color","red");
                    $("#oldPwd").focus();
                }
            },
            error:function () {
                console.log("login.do error。。。")
            }
        })
    });

    $("#content_rignt").on("click","#updPwd",function () {
        var newPwd=$("#newPwd").val();
        var reNewPwd=$("#reNewPwd").val();
        if(newPwd==""||reNewPwd==""){
            $("#errreNewPwd").html("请将表单填写完整").css("color","red");
            $("#errnewPwd").html("");
        }else if(newPwd!=reNewPwd){
            $("#errreNewPwd").html("俩次输入的密码不相同").css("color","red");
            $("#err").html("");
            $("#errnewPwd").html("");
        }else if(newPwd==reNewPwd){
            var reg=/^[0-9]{6}$/;
            if(reg.test(newPwd)){
                //修改密码
                $.ajax({
                    url:"updtePwd.do",
                    type:"post",
                    data:{"cardNo":accNo,"pwd":newPwd},
                    async:false,
                    dataType:"text",
                    success:function (mes) {
                       if(mes.trim()=="updateOk"){
                           if(confirm("密码修改成功！需重新登录！")){
                               window.location="login.html";
                           }

                       }else {
                           $("#errreNewPwd").html(mes).css("color","red");
                       }
                    },
                    error:function () {
                        console.log("updtePwd.do error。。。")
                    }
                })
            }else {
                $("#errnewPwd").html("密码必须为6位数字").css("color","red");
            }
        }
    })

    /*转账*/
    $("#transferBalance").click(function () {
        $("#welcome").css("display","none");
        $("#content_rignt").html(` <div id="transfer">
                    <h3>当前操作：修改密码。请按要求填写完整后点"修改"按钮</h3>
                    <ul>
                        <li>
                            转入卡号：<input id="inAcc" name="inAcc" type="text"/>
                            <span id="errInAcc"></span>
                        </li>
                        <li>
                            转账金额：<input id="balacne" name="balacne" type="text"/>
                            <span id="errbalacne"></span>
                        </li>
                         <li>
                            备注:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <textarea style="resize: none " cols="20" rows="6" id="remark" name="remark"></textarea>
                            <!--<input type="text" id="remark" name="remark"/>-->
                         </li>
                        <li>
                            <input type="button" value="转账" id="upBalance"/>
                        </li>  
                    </ul>
                </div>
           </div>`);
    });

    /*定义转账委托事件*/
    $("#content_rignt").on("click","#upBalance",function () {
        var inAcc=$("#inAcc").val();
        var balacne=$("#balacne").val();
        var remark=$("#remark").val();
        var regAcc=/^[0-9]{16}$/;
        var regBal=/^\d+$/;
        if(regAcc.test(inAcc)&&regBal.test(balacne)){
            //转账
            $.ajax({
                url:"transfer.do",
                type:"post",
                data:{"Outcardno":accNo,"Incardno":inAcc,"tranferAmount":balacne,"remark":remark},
                async:false,
                dataType:"text",
                success:function (mes) {
                 if(mes.trim()=="transferOk"){
                     $("#errbalacne").html("转账成功").css("color","green");
                 }else {
                     $("#errbalacne").html(mes).css("color","red");
                 }
                },
                error:function () {
                    console.log("transfer.do error。。。")
                }
            });

        }else if(!regAcc.test(inAcc)){
            $("#errInAcc").html("账号必须为16位").css("color","red");
        }else if(!regBal.test(balacne)){
            $("#errbalacne").html("金额必须为数字").css("color","red");
        }

    });

    /*查询交易记录*/
    $("#serachTrans").click(function () {
        $("#welcome").css("display","none");
        $("#content_rignt").html(`<div id="query">    
               <h3>当前操作：查询交易记录。请选择事件范围后点"查询"按钮</h3>
               <ul>查询日期范围：
                   <input type="date" id="start" name="start"/>到
                   <input type="date" id="end" name="end"/>
                   <input type="button" value="查询" onclick="load(1)"/>
               </ul>
               <table id="tab">
               <tr >
                    <th>交易日期</th>
                    <th>交易额</th>
                    <th>账户余额</th>
                    <th>交易类型</th>
                    <th>备注</th>
                </tr>
                <tr id="tbody">
                
                </tr>
                  </table>
                <div id="page">       
                    <a href="javascript:;" class="pageChange" >首页</a>
                    <a href="javascript:;" class="pageChange" >上一页</a>
                    <a href="javascript:;" class="pageChange" >下一页</a>
                    <a href="javascript:;" class="pageChange" >尾页</a>
                    第<span id="currPno"></span>页/
                    共<span id="totalPageCount"></span>页
                     (<span id="totalCount"></span>条记录)       
                 </div>                
           </div>`);
        $("#tab").hide();
        $("#page").hide();
    });

    $("#content_rignt").on("click",".pageChange",function () {
       // alert(1);
        var this_=$(this).text();
        var pageNo=parseInt($("#currPno").text());
        var totalPageCount=parseInt($("#totalPageCount").text());
       // alert(totalPageCount);
        if(this_=='首页'){
            pageNo=1;
        }else if (this_=='上一页'){
            if(pageNo==1){
                alert("已经是第一页");
            }else {
                pageNo-=1;
            }
        }else if(this_=='下一页'){
             if(pageNo==totalPageCount){
                 alert("已经是尾页");
             }else {
                pageNo+=1;
             }
        }else {
          pageNo=totalPageCount;
        }
       // alert(this_);
      load(pageNo);
    })
});

function load(pageNo) {
   // alert(1);
    $("#tab").show();
    $("#page").show();
    var start=$("#start").val();
    var end=$("#end").val();
    $.ajax({
        url:"queryByPageInfo.do",
        type:"post",
        data:{"start":start,"end":end,"pageNo":pageNo},
        async:false,
        dataType:"json",
        success:function (mes) {
            if(mes.transactionrecordList!="failed"){
                var str="";
                $(mes.transactionrecordList).each(function () {
                    str+="<tr>"+
                        "<td>"+this.transactiondate+"</td>"+
                        "<td>"+this.transactionamount+"</td>"+
                        "<td>"+this.balance+"</td>"+
                        "<td>"+this.transactiontype+"</td>"+
                        "<td>"+this.remark+"</td>"+
                        "</tr>";
                })
                $("#tbody").html(str);
                $("#currPno").text(mes.page.pageNo);
                $("#totalPageCount").text(mes.page.totalPageCount);
                $("#totalCount").text(mes.page.totalCount);

            }else {
                $("#tbody").html("此时间内没有交易记录");
            }
        },
        error:function () {
            console.log("queryByPageInfo.do error。。。")
        }
    });
}

