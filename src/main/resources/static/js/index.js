/**
 * Created by astro on 2018/4/2.
 */




$(function () {
    console.log("进入index.js");
    var url = '/sell/buyer/product/list';
    var productCategoryList = new Array();
    var getUserUrl = '/sell/buyer/getUserInfo';
    var getAddCar = '/sell/buyer/getAddCar';
    var getAddCarPrice = '/sell/buyer/getAddCarPrice';
    var createOrder = '/sell/buyer/order/create1';
    var payOrder = '/sell/buyer/order/pay';
    var getOrder = '/sell/buyer/getbuyerOrder';

    $.getJSON(url, function (data) {
        console.log(data);
        if (data.code == 0) {
            console.log("success");
            var productListHtml = '';
            var productList = data.data;
            console.log(productList.size);
            productList.map(function (item, index) {
                productCategoryList[index] = item.name;
                //首页商品列表
                var productListItem = item.foods;
                productListItem.map(function (item, index) {
                    productListHtml += ''
                        + '<a href="/sell/index/productDetail?id=' + item.id + '" class="weui-media-box weui-media-box_appmsg">'
                        + '<div class="weui-media-box__hd">'
                        + '<img class="weui-media-box__thumb" src="' + item.icon + '"/>'
                        + '</div>'
                        + '<div class="weui-media-box__bd">'
                        + '<input test =' + item.id + ' type="hidden"/>'
                        + '<h4 class="weui-media-box__title">' + item.name + '</h4>'
                        + '<p class="weui-media-box__desc">' + item.description + '</p>'
                        + '</div>'
                        + '</a>';
                });

            });
            $('.row').html(productListHtml);
        }
    });

    $("#picker").picker({
        title: "选择商品种类",
        cols: [{
            textAlign: 'center',
            values: productCategoryList
        }],

    });

    //搜索商品
    $(".searchBtn").click(function () {
        var searchName = $("#picker").val();
        console.log(searchName);
        $.getJSON(url, function (data) {
            if (data.code == 0) {
                console.log("success");
                var cListHTML = '';
                var cate = data.data;
                cate.map(function (item, index) {
                    if (item.name == searchName) {
                        var cList = item.foods;
                        cList.map(function (item, index) {
                            cListHTML += ''
                                + '<a href="/sell/index/productDetail?id=' + item.id + '" class="weui-media-box weui-media-box_appmsg">'
                                + '<div class="weui-media-box__hd">'
                                + '<img class="weui-media-box__thumb itemIcon" src="' + item.icon + '"/>'
                                + '</div>'
                                + '<div class="weui-media-box__bd">'
                                + '<input test =' + item.id + ' type="hidden"/>'
                                + '<h4 class="weui-media-box__title">' + item.name + '</h4>'
                                + '<p class="weui-media-box__desc">' + item.description + '</p>'
                                + '</div>'
                                + '</a>';
                        });
                    }
                });
                $('.row1').html(cListHTML);

            }
        });
    });

    $(".dtap3").click(function () {
        console.log("getUserInfo");
        //获取个人信息
        $.getJSON(getUserUrl, function (data) {
            if (data.code == 0) {
                console.log(data);
                var headImgHtml = '<img align="middle" src="' + data.data.headImgUrl + '"  />';
                $('.headimg').html(headImgHtml);
            }
        });

        //获取购物车价钱
        $.getJSON(getAddCarPrice, function (data) {
            if (data.code == 0) {
                console.log("getCarPrice");
                console.log(data);
                var getcarPrice = ' <p>待支付：' + data.data + '元</p>';
                $('.getCarPrice').html(getcarPrice);
            }
        });

        //获取购物车信息
        // $.getJSON(getAddCar, function (data) {
        //     if (data.code == 0) {
        //         console.log("getCar");
        //         console.log(data);
        //         var addCarHtml = '';
        //         var addCarMap = data.data;
        //         if (addCarMap != null) {
        //             addCarMap.map(function (item, index) {
        //                 var pname = item.productName;
        //                 addCarHtml += ''
        //                     + '<div class="weui-cell">'
        //                     + ' <div class="weui-cell__hd"></div>'
        //                     + '<div class="weui-cell__bd">'
        //                     + '  <p>' + item.productName + '</p>'
        //                     + ' </div>'
        //                     + ' <div class="weui-cell__ft">x' + item.productNum + '</div>'
        //                     + '</div>'
        //             });
        //             $('.carList').html(addCarHtml);
        //         }
        //
        //     }
        // });
        $.getJSON(getAddCar, function (data) {
            if (data.code == 0) {
                console.log("getCar");
                console.log(data);
                var addCarHtml = '';
                var addCarMap = data.data;
                if (addCarMap != null) {
                    addCarMap.map(function (item, index) {
                        var pname = item.productName;
                        addCarHtml += ''
                            + ' <div class="weui-cell weui-cell_swiped">'
                            + ' <div class="weui-cell__bd" style="transform: translate3d(0px, 0px, 0px);">'
                            + ' <div class="weui-cell">'
                            + ' <div class="weui-cell__bd">'
                            + '  <p>' + item.productName + '</p>'
                            + ' </div>'
                            + '<div class="weui-cell__ft">x' + item.productNum + '</div>'
                            + '</div>'
                            + '  </div>'
                            + ' <div class="weui-cell__ft">'
                            + '<a class="weui-swiped-btn weui-swiped-btn_warn delete-swipeout" href="javascript:">删除</a>'
                            + ' <a class="weui-swiped-btn weui-swiped-btn_default close-swipeout" href="javascript:">关闭</a>'
                            + ' </div>'
                            + ' </div>'
                    });
                    $('.carList').html(addCarHtml);
                }

            }
        });

        //获取订单
        $.getJSON(getOrder, function (data) {
            if (data.code == 0) {
                console.log("getOrder");
                console.log(data);
                var getOrderHtml = '';
                var orderList = data.data;
                if (orderList != null) {
                    orderList.map(function (item, index) {
                        index+=1;
                        getOrderHtml += ''
                            + ' <a class="weui-cell weui-cell_access" href="/sell/index/orderDetail?id=' + item.orderId + '">'
                            + '<div class="weui-cell__bd">'
                            + '<p>订单:'+index+'</p>'
                            + '</div>'
                            + '<div class="weui-cell__ft">'
                            + ' </div>'
                            + '</a>'

                    });
                    $('.orderList').html(getOrderHtml);
                }


            }
        });
    });
    // <div class="weui-cell weui-cell_swiped">
    //     <div class="weui-cell__bd" style="transform: translate3d(0px, 0px, 0px);">
    //     <div class="weui-cell">
    //     <div class="weui-cell__bd">
    //     <p>左滑列表</p>
    //     </div>
    //     <div class="weui-cell__ft">向左滑动试试</div>
    //     </div>
    //     </div>
    //     <div class="weui-cell__ft">
    //     <a class="weui-swiped-btn weui-swiped-btn_warn delete-swipeout" href="javascript:">删除</a>
    //     <a class="weui-swiped-btn weui-swiped-btn_default close-swipeout" href="javascript:">关闭</a>
    //     </div>
    //     </div>

    $(".pay").click(function () {

        var name = $('.buyername').val();
        var address = $('.buyeraddress').val();
        var phone = $('.buyerphone').val();

        var formData = new FormData();
        formData.append("name", name);
        formData.append("address", address);
        formData.append("phone", phone);

        console.log("fromDate:" + formData);

        $.ajax({
            url: createOrder,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.code == 0) {
                    console.log("下单成功");
                    $.confirm({
                        title: '支付',
                        text: '请确定支付',
                        onOK: function () {
                            console.log("下单成功1");
                            console.log(data.data.orderid);
                            pay(data.data.orderid);
                        },
                        onCancel: function () {
                            console.log("下单成功");
                        }
                    });
                } else {

                }
            }
        });
    });

    function pay(orderid) {
        payOrder = payOrder + "?orderid=" + orderid;

        $.ajax({
            url: payOrder,
            type: 'POST',
            data: {},
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {

            }
        });
    }


});


// <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
//     <div class="weui-media-box__hd">
//     <img class="weui-media-box__thumb" src="/sell/images/my/p1.jpg"/>
//     </div>
//     <div class="weui-media-box__bd">
//     <h4 class="weui-media-box__title">手撕鸡</h4>
//     <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
// </div>
// </a>


// var productListHtml='';
// var productList=data.data;
// console.log(productList);
// productList.map(function  (item,index){
//     console.log(item);
//
//     productListHtml +=''
//         +'<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">'
//         +'<div class="weui-media-box__hd">'
//         +'<img class="weui-media-box__thumb" src="/sell/images/my/p1.jpg"/>'
//         +'</div>'
//         +'<div class="weui-media-box__bd">'
//         +'<h4 class="weui-media-box__title">'+item.name+'</h4>'
//         +'<p class="weui-media-box__desc">'+item.description+'</p>'
//         +'</div>'
//         +'</a>';
// });
// $('.row').html(productListHtml);