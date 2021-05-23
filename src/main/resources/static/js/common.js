// 根据名字获取对应的cookie
function getCookieByName(cookieName) {
    var value = "";
    if (cookieName == null || cookieName == "" || cookieName == undefined) {
        return value;
    }

    var allCookies = document.cookie;
    var cookieIdxBegin = allCookies.indexOf(cookieName);
    if (cookieIdxBegin != -1) {
        cookieIdxBegin = cookieIdxBegin + cookieName.length + 1;
        // 取对应cookie值的结束索引
        var cookieIdxEnd = allCookies.indexOf(";", cookieIdxBegin);
        if (cookieIdxEnd == -1) {
            cookieIdxEnd = allCookies.length;
        }
        // 获取最终的cookie值
        value = unescape(allCookies.substring(cookieIdxBegin, cookieIdxEnd));
    }
    return value;
};

// 将csrfToken设置到POST表单
var loadCsrfToken = function () {
    var csrfToken = getCookieByName("XSRF-TOKEN");
    $("input[name=_csrf]").val(csrfToken);
};

var displayCsrfToken = function () {
    var csrfToken = getCookieByName("XSRF-TOKEN");
    $("#csrf-token-txt").text(csrfToken);
};