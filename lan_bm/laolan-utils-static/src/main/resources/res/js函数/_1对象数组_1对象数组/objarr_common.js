let Objarr = function (param) {
    return this;
}

Objarr.prototype.init = function () {
    return this;
}

//前端分组： 原始
Objarr.prototype.getObjArrGroupJs = function (arr) {
    var map = {},
        dest = [];
    for (var i = 0; i < arr.length; i++) {
        var ai = arr[i];
        if (!map[ai.line_code]) {
            dest.push({
                line_code: ai.line_code,
                objItems: [ai]
            });
            map[ai.line_code] = ai;
        } else {
            for (var j = 0; j < dest.length; j++) {
                var dj = dest[j];
                if (dj.line_code == ai.line_code) {
                    dj.objItems.push(ai);
                    break;
                }
            }
        }
    }
    return dest;
}

//前端分组： //1.分组转换： 单条件(一个对象数组转换成多个对象数组)
Objarr.prototype.getObjArrGroup = function (arr, paramArr) {
    var map = {},
        dest = [];
    for(var i = 0; i < arr.length; i++){
        var ai = arr[i];
        if(!map[ai[paramArr[0]]]){
            var destTemp = {};//多条件分组
            destTemp[paramArr[0]] = ai[paramArr[0]];
            destTemp['objItems'] = [ai];
            dest.push(destTemp);
            map[ai[paramArr[0]]] = ai;
        }else{
            for(var j = 0; j < dest.length; j++){
                var dj = dest[j];
                if(dj[paramArr[0]] == ai[paramArr[0]]){
                    dj.objItems.push(ai);
                    break;
                }
            }
        }
    }
    return dest;
}

//前端分组：
/**
 * 1.分组转换(一个对象数组转换成多个对象数组)
 *		对象map(key,value) key是一个（查询条件）_1对象数组_2数组，value是要放回的格式
 *		数组dest，最后结果dest(map,map,map...)
 * 2.参数：_1对象数组_2数组，分组条件，map的名称（分组后集合的名称）
 */
Objarr.prototype.getObjArrGroup2 = function (arr, paramArr) {
    var map = {},
        dest = [];
    for(var i = 0; i < arr.length; i++){
        var ai = arr[i];

        var keyTemp = [];//map的这个key是否存在
        var myFunction1 = function(item, index) {
            keyTemp.push(ai[item]);
        }
        paramArr.forEach(myFunction1);
        //判断key，添加是否匹配
        if(!map[keyTemp]){//1.创建不同的对象
            //拼接此map的值value
            var destTemp = {};//map多条件
            var myFunction11 = function(item, index) {
                destTemp[item] = ai[item];
            }
            paramArr.forEach(myFunction11);
            // 取消'data'命名，为了不冲突，改为
            // if(dataName) {
            //     dataName = 'data';
            // }
            // destTemp[dataName] = [ai];
            destTemp['objItems'] = [ai];
            dest.push(destTemp);

            //加入新key的map
            var paramArrTemp = [];
            var myFunction12 = function(item, index) {
                paramArrTemp.push(ai[item]);
            }
            paramArr.forEach(myFunction12);
            map[paramArrTemp] = ai;
        }else{//2.匹配条件并添加相应map
            for(var j = 0; j < dest.length; j++){
                var dj = dest[j];
                var flag2 = false;
                var myFunction2 = function(item, index) {
                    //此时map的条件与新列不等，会创建新map
                    //由于本else是匹配条件添加的
                    if(dj[item] != ai[item]){
                        flag2 = true;
                    }
                }
                paramArr.forEach(myFunction2);
                if(!flag2){//条件匹配（false）添加
                    // if(dataName) {
                    //     dataName = 'data';
                    // }
                    // dj[dataName].push(ai);
                    dj['objItems'].push(ai);
                    break;
                }
            }
        }
    }
    return dest;
}

//数组求差（类似二维数组，双重循环）,目前单条件,得第一位的
Objarr.prototype.getObjarrSubtract = function (unionArr, subsetArr, condition) {
    var new_tmp = [];
    for (var i = 0; i < unionArr.length; i++) {
        var flag = true;
        for (var j = 0; j < subsetArr.length; j++) {
            if (unionArr[i][condition] == subsetArr[j][condition]) {
                flag = false;
            }
        }
        if (flag) {
            new_tmp.push(unionArr[i]);
        }
    }
    return new_tmp;
}

//更具条件进行选择 item{ key, value } , 类似后台where 条件
Objarr.prototype.getObjArrWhere= function (Objarr, items) {
    let ObjArr2 = [];
    for (let i = 0; i < Objarr.length; i++) {
        let count = 0;
        // 循环判断条件满足并计数
        items.forEach((item, index) => {
            if(item.value.indexOf(Objarr[i][item.key]) + 1) {
                count += 1;
            } else {
                count += 0;
            }
        });
        if(items.length == count) {
            ObjArr2.push(Objarr[i]);
        }
    }
    return ObjArr2;
}

//数组中，根据一个对象找同一个对象的其他值
Objarr.prototype.getValByItem = function (Objarr, item, resKey) {
    for (let i = 0; i < Objarr.length; i++) {
        if(item.value.indexOf(Objarr[i][item.key]) + 1) {
            return Objarr[i][resKey];
        }
    }
    return "";
}


let objarr = new Objarr().init();

