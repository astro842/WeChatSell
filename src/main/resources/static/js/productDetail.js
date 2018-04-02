/**
 * Created by astro on 2018/4/2.
 */
$(function () {

    var MAX = 99;
    var MIN = 1;
     console.log("detailpage");
     var productId=getQueryString("id");
     var url = '/sell/buyer/product/detail?id='+productId;
     var oneProduct=new Object();
     var productPrice;

    $.getJSON(url, function (data) {
        console.log(data);
        var product=data.data;
        var productName=product.productName;
        var productCategory=product.categoryType;
        var productStock=product.productStock;
        var productDec=product.productDescription;
         productPrice=product.productPrice;

        $('.demos-title').html(productName);
        $('.proCategory').append(productCategory);
        $('.productStock').append(productStock);
        $('.proDec').append(productDec);
        $('.price').html("单价："+productPrice);

    });

    $('.addCar').click(function(e) {
        oneProduct.productId=productId;
        oneProduct.productNum=$('.selectNum').val();
        console.log(oneProduct.productId);
        console.log(oneProduct.productNum);
        $.toast("操作成功");
        setTimeout(function () {
            window.location.href="/sell/index/index";
        },1000);

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

