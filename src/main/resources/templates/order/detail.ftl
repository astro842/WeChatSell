<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <!-- 边栏-->
<#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>订单ID</th>
                                <th>订单总金额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.orderAmount}</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12 column">
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>商品ID</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>

                        <div class="row clearfix">
                            <div class="col-md-12 column">
                            <#if orderDTO.getOrderStatusEnum().getMsg() == "新订单">
                                <a href="/sell/seller/order/finish?orderid=${orderDTO.orderId}" type="button" class="btn btn-primary btn-lg">完结订单</a>
                                <a href="/sell/seller/order/cancel?orderid=${orderDTO.orderId}" type="button" class="btn btn-danger btn-lg">取消订单</a>
                            </#if>
                            </div>
                        </div>
                    </div>


                </div>

        </div>
    </div>

</div>


</div>

</body>

</html>
