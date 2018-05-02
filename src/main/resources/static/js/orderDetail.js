/**
 * Created by astro on 2018/4/2.
 */
$(function () {

     console.log("orderdetailpage");
     var orderId=getQueryString("id");
     var url = '/sell/buyer/order/detail?orderid='+orderId;
     var cancleOrder='/sell/buyer/order/cancle?orderid='+orderId;
     var payOrder='';


    $.getJSON(url, function (data) {
        console.log(data);
        var orderDetailHtml='';
        var orderDetailList=data.data.orderDetailList;
        console.log(orderDetailList);
        if (data.code==0){
            if (orderDetailList!=null){
                orderDetailList.map(function (item,index) {
                    orderDetailHtml +=''
                        + '<a href="javascript:;" class="weui-media-box weui-media-box_appmsg">'
                        + '<div class="weui-media-box__hd">'
                        + '<img class="weui-media-box__thumb itemIcon" src="' + item.productIcon + '"/>'
                        + '</div>'
                        + '<div class="weui-media-box__bd">'
                        + '<h4 class="weui-media-box__title">' + item.productName + '</h4>'
                        + '<p class="weui-media-box__desc">' + item.productPrice+ '元&nbsp;&nbsp;x'+item.productQuantity+'个</p>'
                        + '</div>'
                        + '</a>';
                });

                var status=data.data.orderStatus;
                var statusHtml='';
                if (status==0){
                    statusHtml=' <a  class="weui-btn weui-btn_warn btnr cancleO">取消订单</a>'
                               +'<a href="javascript:;" class="weui-btn weui-btn_primary btng">支付</a>';

                }else if(status==1){
                    statusHtml= '<a href="javascript:;" class="weui-btn_disabled weui-btn weui-btn_primary btns">已完结</a>';
                }else if(status==2){
                    statusHtml= '<a href="javascript:;" class="weui-btn_disabled weui-btn weui-btn_warn btns">已取消</a>';
                }
                $(".twoBtn").html(statusHtml);
                $(".orderDetailList").html(orderDetailHtml);
            }
        }

    });


    $(".cancleO").click(function () {
        $.getJSON(cancleOrder, function (data) {
            if (data.code==0){
                console.log("取消订单成功");
            }
        });
    });




});


