$(function () {
    var shopId = getQueryString("shopId");
    alert(shopId);
    var shopInfoUrl = "/project2/shop/getshopmanagementinfo?shopId=" + shopId;
    alert("测试shopmanage");
    $.getJSON(shopInfoUrl, function (data) {
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
        }
         $("#shopInfo").attr("href", "/project2/shopadmin/shopedit?shopId=" + shopId);
    });

});