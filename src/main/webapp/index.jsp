<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
    function readTest() {
        var data = {};

        data.pagenumber = -1; // 当前页数(如果不进行分页，该条数据默认为-1)
        // data.pagesize = 2; // 每页的数据量
        data.version = '1.1'; // 版本号
        $.ajax({
            url : "article/deleteByArticleid",
            type : "POST",
            data : JSON.stringify(data),
            contentType : "application/json;charset=UTF-8",
            success : function(result) {
                var _str = JSON.stringify(result);
                alert(_str);
            },
            error : function() {
                alert("error");
            }
        });

    }
</script>
</head>

<body>

<H1>Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!</H1>
<form method="post" action="<%=request.getContextPath()%>/upload/file" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="file" />
    <br/><%=request.getContextPath()%><br/>
    <input type="submit" value="上传" />
</form>
<input type="button" id="s3" value="readTest" onclick="readTest();"/>
</body>
</html>
