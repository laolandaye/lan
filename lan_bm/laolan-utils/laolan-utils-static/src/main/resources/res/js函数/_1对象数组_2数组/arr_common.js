let Arr = function (param) {
    return this;
}

Arr.prototype.init = function () {
    return this;
}

//获得数组差（arr1为大数组）
Arr.prototype.getArrSubtract = function (arr1, arr2) {
    for (var i = arr1.length - 1; i >= 0; i--) {
        a = arr1[i];
        for (var j = arr2.length - 1; j >= 0; j--) {
            b = arr2[j];
            if (a == b) {
                arr1.splice(i, 1);
                arr2.splice(j, 1);
                break;
            }
        }
    }
    return arr1;//得到差
}

//4.在对象数组中，抽取一列，组成数据
Arr.prototype.getArrFromObjArr = function(Objarr, key) {
    let arr = [];
    for (let i = 0; i < Objarr.length; i++) {
        arr.push(Objarr[i][key]);
    }
    return arr;
}



let arr = new Arr().init();

