<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function createImage() {
        console.log('create')
        document.getElementsByTagName('img')[0].src = '/get-code?ts=' + Math.random()
    }
    function check() {
        console.log('check')
        if (!$("#code").val()) {
            alert('请输入验证码')
            return
        }
        $.getJSON('check-code?code='+$("#code").val(), function (result) {
            alert(result)
        })
    }
</script>
</head>
<body>
<h2>Hello World!</h2>
<img src="/get-code" onclick="createImage()"/>
<div>${test}</div>
<input id="code" type="text" placeholder="输入验证码">
<input type="button" value="检查" onclick="check()"/>
</body>
</html>
