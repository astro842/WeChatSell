
<html>
 <head>
     <meta charset="UTF-8">
     <title>卖家商品列表</title>
     <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
 </head>
<body>

<div class="container">
    <div class="row clearfix">

        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="alert alert-success alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4>
                            success!
                        </h4> <strong>${msg!"成功"}</strong> <a href="${url}" class="alert-link">3秒后自动跳转</a>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
<script>
    setTimeout('location.href="${url}"',3000);

</script>

</html>
