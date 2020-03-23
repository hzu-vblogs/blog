var FormToJson = function () {
    var formToJson =     function(f) {
        var fd = $(f).serializeArray();
        var d = {};
        $(fd).each(function() {
            if (d[this.name] !== undefined){
                if (!Array.isArray(d[this.name])) {
                    d[this.name] = [d[this.name]];
                }
                d[this.name].push(this.value);
            }else{
                d[this.name] = this.value;
            }
        });
        return d;
    };


    return {
        handleFormToJson:function (f) {
            return formToJson(f);
        }
    }
}();