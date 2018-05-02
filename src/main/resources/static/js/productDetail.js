/**
 * Created by astro on 2018/4/2.
 */
$(function () {

    var MAX = 99;
    var MIN = 1;
     console.log("detailpage");
     var productId=getQueryString("id");
     var url = '/sell/buyer/product/detail?id='+productId;
     var productPrice;
     var productListKey='__produstList';

     var addCarUrl='/sell/buyer/addcar';

    $.getJSON(url, function (data) {
        console.log(data);
        var product=data.data;
        var productName=product.productName;
        var productCategory=product.categoryType;
        var productStock=product.productStock;
        var productDec=product.productDescription;
         productPrice=product.productPrice;
        var productIcon=product.productIcon;

        var productIconHtml='';
            productIconHtml=''+'<img class="pp" src="'+productIcon+'" alt=""/>';

        $('.demos-title').html(productName);
        $('.proCategory').append(productCategory);
        $('.productStock').append(productStock);
        $('.proDec').append(productDec);
        $('.price').html("单价："+productPrice);
        $('.heimg').html(productIconHtml);

    });

    $('.addCar').click(function() {
       // localStorage.setItem(productListKey, JSON.stringify(producList))

        var productNum= $(".selectNum").val();
        var addOneCarUrl=addCarUrl+"?productId="+productId+"&productNum="+productNum;
        console.log("productId:"+productId);
        console.log("prodctNum:"+productNum);
        $.getJSON(addOneCarUrl, function (data) {
            if(data.code==0){
                console.log("操作成功");
                              $.toast("操作成功");
                            setTimeout(function () {
                                //window.location.href="/sell/index/index";
                                window.history.go(-1);
                            },1000);
            }

        });


        // $.ajax({
        //     url: createOrder,
        //     type: 'POST',
        //     data: ,
        //     contentType: false,
        //     processData: false,
        //     cache: false,
        //     success: function (data) {
        //         if (data.code == 0) {
        //             console.log("操作成功");
        //               $.toast("操作成功");
        //             setTimeout(function () {
        //                 //window.location.href="/sell/index/index";
        //                 window.history.go(-1);
        //             },1000);
        //         } else {
        //
        //         }
        //     }
        // });



    });


    $('.weui-count__decrease').click(function(e) {
        var $input = $(e.currentTarget).parent().find('.weui-count__number');
        var number = parseInt($input.val() || "0") - 1;
        if (number < MIN) number = MIN;
        $input.val(number);
        var p = number *productPrice;
        p=p.toFixed(2);
        console.log(productPrice);
        $('.price').html("合计："+p);


    });
    $('.weui-count__increase').click(function(e) {
        var $input = $(e.currentTarget).parent().find('.weui-count__number');
        var number = parseInt($input.val() || "0") + 1;
        if (number > MAX) number = MAX;
        $input.val(number);
        var p = number *productPrice;
        p=p.toFixed(2);
        console.log(productPrice);
        $('.price').html("合计："+p);
    });

});

