var Main = new Vue({
    el: '#app',
    data() {
        return {
        };
    },
    created: function(){
        // this.prepareData();
        this.prepareData2(
            ['getDegrees', 'getTeamInfos', 'getTopicInfos', 'getLevelInfos'],
            ['pageDpDevPhysicTabs', 'pageDpDevPhysicTabs2'],
        );
    },
    methods: {
        prepareData2:function(prepareFns, nextFns) {
            let self = this;
            async function awaitAll(...fnArr) {
                if ({}.toString.call(fnArr[0]) === '[object Array]') {
                    fnArr = fnArr[0];
                }
                const fnNum = fnArr.length;
                const promiseArr = [];
                const resultArr = [];
                for (let i = 0; i < fnNum; i++) {
                    promiseArr.push(fnArr[i]());
                }
                for (let i = 0; i < fnNum; i++) {
                    resultArr.push(await promiseArr[i])
                }
                return resultArr;
            }

            // 循环获得
            let fns = [];
            for (let i = 0; i < prepareFns.length; i++) {
                fns.push(async function() {
                    return new Promise((resolve, reject) => {
                        self[prepareFns[i]](resolve);// 1.4 获得唯度
                    })
                })
            }

            var fn_1 = async function() {
                //等待异步操作执行完了后执行的方法
                awaitAll(fns).then(results => {
                    for (let i = 0; i < nextFns.length; i++) {
                       self[nextFns[i]]();
                    }
                });
            }
            fn_1();
        },
        prepareData:function() {
            let self = this;
            async function awaitAll(...fnArr) {
                if ({}.toString.call(fnArr[0]) === '[object Array]') {
                    fnArr = fnArr[0];
                }
                const fnNum = fnArr.length;
                const promiseArr = [];
                const resultArr = [];
                for (let i = 0; i < fnNum; i++) {
                    promiseArr.push(fnArr[i]());
                }
                for (let i = 0; i < fnNum; i++) {
                    resultArr.push(await promiseArr[i])
                }
                return resultArr;
            }

            async function fn1() {
                return new Promise((resolve, reject) => {
                    self.getDegrees(resolve);// 1.4 获得唯度
                })
            }
            async function fn2() {
                return new Promise((resolve, reject) => {
                    self.getTeamInfos(resolve);// 1.1获得团队
                })
            }
            async function fn3() {
                return new Promise((resolve, reject) => {
                    self.getTopicInfos(resolve);// 1.2获得主题
                })
            }
            async function fn4() {
                return new Promise((resolve, reject) => {
                    self.getLevelInfos(resolve);// 1.3所属行业
                })
            }
            var fn_1 = async function() {
                //等待异步操作执行完了后执行的方法
                awaitAll(fn1, fn2, fn3, fn4).then(results => {
                    self.pageDpDevPhysicTabs();
                    self.pageDpDevPhysicTabs2();
                });
            }
            fn_1();
        },
        getDegrees: function (resolve) {
            axios.get('tsconfig.json')
                .then(function (response) {
                    if(resolve) {
                        resolve(response);
                    }
                    console.log('1');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getTeamInfos: function (resolve) {
            axios.get('tsconfig.json')
                .then(function (response) {
                    if(resolve) {
                        resolve(response);
                    }
                    console.log('2');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getTopicInfos: function (resolve) {
            axios.get('tsconfig.json')
                .then(function (response) {
                    if(resolve) {
                        resolve(response);
                    }
                    console.log('3');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getLevelInfos: function (resolve) {
            axios.get('tsconfig.json')
                .then(function (response) {
                    if(resolve) {
                        resolve(response);
                    }
                    console.log('4');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        pageDpDevPhysicTabs: function (resolve) {
            axios.get('tsconfig.json')
                .then(function (response) {
                    console.log('A');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        pageDpDevPhysicTabs2: function (resolve) {
            axios.get('tsconfig.json')
                .then(function (response) {
                    console.log('B');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    }
});