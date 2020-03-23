// 函数对象

var Validate = function () {
    // 正常的函数
    var handleIniValidate = function () {


        //所有表单id为inputForm的表单都被初始化验证，固定了
        $("#inputForm").validate({
            // 出错的提示元素 提示语
            errorElement: 'span',
            errorClass: 'help-block',
            // error是展示提示语的元素，element是出错的元素
            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");

                error.insertAfter(element);
            }

        });
        console.log("初始化验证表单");
    };
    //添加自定义验证规则
    var addCunstomedValidateRule = function () {
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");
    };


    return {
        init: function () {
            handleIniValidate();
            addCunstomedValidateRule();
        }
    }
}();


$(function () {
    Validate.init();
});