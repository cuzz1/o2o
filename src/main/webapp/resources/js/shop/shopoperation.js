
$(function () {
   // 获取初始url
   var initUrl = "/project2/shop/getshopinitinfo";
   var registerShopUrl = "/project2/shop/registershop";
   alert(initUrl);
   getShopInitInfo();


   function getShopInitInfo(){
      // 获取初始值
      $.getJSON(initUrl, function (data) {
          alert("SSSSSSSS");
         if (data.success) {
            var tempHtml = "";
            var tempAreaHtml = "";
            data.shopCategoryList.map(function (item, index) {
               tempHtml += "<option data-id='" + item.shopCategoryId + "'>"
                + item.shopCategoryName + "</option>";
            });
            
            data.areaList.map(function (item, index) {
                tempAreaHtml +=  "<option data-id='" + item.areaId + "'>"
                    + item.areaName + "</option>";
            });
            $("#shop-category").html(tempHtml);
            $("#area").html(tempAreaHtml);
         }
      });
   }
      // 提交
      // $("#submit").click(function () {
      //    var shop = {};
      //    shop.shopName = $("#shop-name").val();
      //    shop.shopAddr = $("#shop-addr").val();
      //    shop.phone = $("#shop-phone").val();
      //    shop.shopDesc = $("#shop-desc").val();
      //    shop.shopCategory = {
      //       shopCategoryId:$("#shop-category").find("option").not(function () {
      //           return !this.selected;
      //       }).data("id")
      //    };
      //    shop.area = {
      //       areaId: $("#area").find("option").not(function () {
      //          return !this.selected;
      //       }).data("id")
      //    };
      //    var shopImg = $("#shop-img")[0].files[0];
      //    var formData = new FormData();
      //    formData.append("shopImg", shopImg);
      //    formData.append("shopStr", JSON.stringify(shop));
      //    var verifyCodeActual = $("#j_captcha").val();
      //    // 判断是否为空
      //    if (!verifyCodeActual) {
      //        $.toast("请输入验证码!");
      //        return;
      //    }
      //    formData.append("verifyCodeActual", verifyCodeActual);
      //    // 提交到后台
      //     $.ajax({
      //         url: registerShopUrl,
      //         type: "POST",
      //         data: formData,
      //         contentType: false,
      //         processDate: false,
      //         cache: false,
      //         success: function (data) {
      //            if (data.success){
      //                $.toast("提交成功!");
      //            } else {
      //                $.toast("提交失败" + data.errMsg);
      //            }
      //            // 不管没有提交成功 都更换验证码
      //            $("#captcha_img").click();
      //         }
      //
      //     });
      // });

    $('#submit').click(function() {
        var shop = {};

        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();

        shop.shopCategory = {
            shopCategoryId : $('#shop-category').find('option').not(function() {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId : $('#area').find('option').not(function() {
                return !this.selected;
            }).data('id')
        };

        var shopImg = $("#shop-img")[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码！');
            return;
        }
        formData.append("verifyCodeActual", verifyCodeActual);
        $.ajax({
            url : registerShopUrl,
            type : 'POST',
            // contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data : formData,
            contentType : false,
            processData : false,
            cache : false,
            success : function(data) {
                if (data.success) {
                    $.toast('提交成功！');
                    // if (isEdit){
                    //     $('#captcha_img').click();
                    // } else{
                    //     window.location.href="/shop/shoplist";
                    // }
                } else {
                    $.toast('提交失败！');
                    $('#captcha_img').click();
                }
            }
        });
    });
});