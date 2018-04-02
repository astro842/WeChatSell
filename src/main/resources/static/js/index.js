/**
 * Created by astro on 2018/4/2.
 */
$(function () {

     console.log("sssss");
    var url = '/sell/buyer/product/list';

    $.getJSON(url, function (data) {
        console.log(data);
        if(data.code==0){
            console.log("success");

       var productListHtml='';
       var productList=data.data;
            console.log(productList);
            productList.map(function  (item,index){
                console.log(item);
                var productListItem=item.foods;
                productListItem.map(function (item,index) {
                    productListHtml +=''
                        +'<a href="/sell/index/productDetail?id='+item.id+'" class="weui-media-box weui-media-box_appmsg">'
                    +'<div class="weui-media-box__hd">'
                    +'<img class="weui-media-box__thumb" src="/sell/images/my/p1.jpg"/>'
                    +'</div>'
                    +'<div class="weui-media-box__bd">'
                    +'<input test ='+item.id+' type="hidden"/>'
                    +'<h4 class="weui-media-box__title">'+item.name+'</h4>'
                    +'<p class="weui-media-box__desc">'+item.description+'</p>'
                    +'</div>'
                    +'</a>'; });

            });
           // $('.proList').html(productList);
            $('.row').html(productListHtml);
        }
    });

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