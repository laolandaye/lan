let Validator = function (param) {
    return this;
}

Validator.prototype.init = function () {
    return this;
}

Validator.prototype.checkAge =  (rule, value, callback) => {
    if (!value) {
        return callback(new Error('年龄不能为空'));
    }
    setTimeout(() => {
        if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
        } else {
            if (value < 18) {
                callback(new Error('必须年满18岁'));
            } else {
                callback();
            }
        }
    }, 1000);
};

// 没有什么是闭包不能解决的
// 密码验证
// validator.validatePass(this, 'ruleForm', 'ruleForm','pass', 'checkPass')  对应方式vm[]
// validator.validatePass(this, ruleForm, ruleForm,pass, checkPass) 对应方式vm.  各种校验方便，看自己取舍
Validator.prototype.validatePass = function (vm, formRef, formName, pwd, rePwd) {
    return (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入密码'));
        } else {
            if (vm[formName][rePwd] !== '') {
                vm.$refs[formRef].validateField(rePwd);
            }
            callback();
        }
    }
}

// 密码二次验证
Validator.prototype.validatePass2 = function (vm, formRef, formName, pwd, rePwd) {
    return (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请再次输入密码'));
        } else if (value !== vm[formName][pwd]) {

            callback(new Error('两次输入密码不一致!'));
        } else {
            callback();
        }
    }
}

//邮箱验证
Validator.prototype.validateEmail = (rule, value, callback) => {
    if (value != null && value.length > 0 && /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/.test(value) == false && /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(value) == false) {
        callback(new Error("请输入正确邮箱"));
    } else {
        callback();
    }
}

// 电话验证
Validator.prototype.validatePhone = (rule,value,callback) => {
    if(/^1[345789]\d{9}$/.test(value) == false&&/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(value) ==false){
        callback(new Error("请输入正确手机/电话号"));
    }else{
        callback();
    }
}

let validator = new Validator().init();