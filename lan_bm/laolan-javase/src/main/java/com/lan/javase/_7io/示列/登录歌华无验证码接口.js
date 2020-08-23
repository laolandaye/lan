/* https://datagov.beijingcloud.com.cn/dam/login */
/*
    /ftl/framework/bjghFramework_pro
    /api/directory/index
*/
var aesIvKey = "7b51fd7053196308";
var contextPath = vm.contextPath
// var url = contextPath + "/login?redirect=" + encodeURIComponent("/api/directory/index");
var url = contextPath + "/login?redirect=" + encodeURIComponent("/ftl/framework/bjghFramework_pro");
var data = "userId=" + encodeURIComponent(vm.aesEncrypt("dam")) +
    "&pwd=" + encodeURIComponent(vm.aesEncrypt("Abcd!234")) +
    "&callback=" +
    "&checkcode="

axios.post(url, data).then(function (res) {
    var data = res.data;
    if (data.success) {
        //添加sso
        function _redirect() {
            var regExp = new RegExp("@@", "g");
            location.href = data.redirect.replace(regExp, "&");
        }
        axios.get( contextPath + "/dam/sso/ssologin.js", {
            params: {
                su: data.userId
            },
            headers: {
                "Content-Type": "text"
            }
        }).then(function (ssodata) {
            var urls = ssodata.data.split(",");
            var succStateCount = urls.length;
            for (var i = 0; i < urls.length; i++) {
                var iframe = document.createElement("iframe");
                iframe.style.display = "none";
                iframe.src = urls[i] + "?su=" + data.userId;
                iframe.width = "0px";
                iframe.height = "0px";
                var _callback = function () {
                    succStateCount--;
                };
                if (iframe.attachEvent) {
                    iframe.attachEvent("onload", _callback);
                    iframe.attachEvent("onerror", _callback);
                } else {
                    iframe.onload = _callback;
                    iframe.onerror = _callback;
                }
                document.body.appendChild(iframe);
            }
            var timeout = 2000;

            setInterval(function () {
                if (succStateCount === 0) {
                    _redirect();
                }
            }, 500);

            setTimeout(_redirect, timeout);
        }).catch(function () {
            _redirect();
        });
    } else {
    }
});