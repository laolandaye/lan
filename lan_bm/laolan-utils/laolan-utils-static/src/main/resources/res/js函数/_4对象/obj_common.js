let Obj = function (param) {
    return this;
}

Obj.prototype.init = function () {
    return this;
}

// 对象初始化 这里使用 基本数据类型typeof, 引用数据类型instanceof
// https://blog.csdn.net/yCharlee/article/details/52424603
Obj.prototype.initObj = function (obj) {
    function typeofType(item) {
        switch (typeof item) {
            case "number":
                return 0;
                break;
            case "string":
                return '';
                break;
            case "boolean":
                return false;
                break;
            case "object":
                // 引用数据类型单独判断
                if(item instanceof Array) {
                    return []
                } else if(item instanceof Object) {
                    // 这里也会包含date类型，实际开发过程都是string
                    return {};
                } else {
                }
                break;
            case "function":
                return null;
                break;
            default:
        }
    }
    for(let key in obj) {
        obj[key] =  typeofType(obj[key])
    }
    return obj;
}



let obj = new Obj().init();

