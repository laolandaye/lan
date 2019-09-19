let ArrObjarr = function (param) {
    return this;
}

ArrObjarr.prototype.init = function () {
    return this;
}

//js拆分数组
ArrObjarr.prototype.splitArrObjarr = function (arrObjarr) {
    var allData = []; //用来装处理完的数组
    var currData = []; //子数组用来存分割完的数据
    //循环需要处理的数组
    for (var i = 0; i < arrObjarr.length; i++) {
        //将chartArr[i]添加到子数组
        currData.push(arrObjarr[i]);
        //在这里求4的余数,如果i不等于0,且可以整除 或者考虑到不满4个或等于4个的情况就要加上  i等于当前数组长度-1的时候
        if ((i != 0 && (i + 1) % 17 == 0) || i == arrObjarr.length - 1) {
            if (i < arrObjarr.length - 1) {//这里做个小变动，取后面的第一条加入数组
                currData.push(arrObjarr[i + 1]);
            }
            //把currData加到allData里
            allData.push(currData);
            //在这里清空currData
            currData = [];
        }
    }
    return allData;
}



let arrObjarr = new ArrObjarr().init();

