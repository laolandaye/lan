var Main = new Vue({
    el: '#app',
    data() {
        let validatorFillDate = (rule,value,callback) => {
            let self = this;
            if(value && self.formCompanyEnterp.companyId && self.formCompanyEnterp.formType == 'add') {
                axios.get(self.contextPath + '/api/companyEnterp/checkFillDate', {
                    params: {
                        companyId: self.formCompanyEnterp.companyId,
                        fillDate: value,
                    }
                }).then(function (res) {
                    const data = res.data;
                    switch (data.result) {
                        case "0000":
                            callback();
                            break;
                        case "9999":
                            callback(new Error(data.msg));
                            break;
                        default:
                    }
                }).catch(function (err) {
                    callback(new Error(err));
                });
            }
        };
        return {
            ruleForm: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: '',
                pass: '',
                checkPass: '',
                age: '',
                email: '',
                phone: '',
            },
            rules: {
                name: [
                    { required: true, message: '请输入活动名称', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                region: [
                    { required: true, message: '请选择活动区域', trigger: 'change' }
                ],
                date1: [
                    { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                ],
                date2: [
                    { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
                ],
                type: [
                    { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
                ],
                resource: [
                    { required: true, message: '请选择活动资源', trigger: 'change' }
                ],
                desc: [
                    { required: true, message: '请填写活动形式', trigger: 'blur' }
                ],
                pass: [
                    { validator: validator.validatePass(this, 'ruleForm', 'ruleForm','pass', 'checkPass'), trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validator.validatePass2(this,'ruleForm', 'ruleForm', 'pass', 'checkPass'), trigger: 'blur' }
                ],
                age: [
                    { validator: validator.checkAge, trigger: 'blur' }
                ],
                email: [
                    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                ],
                phone: [
                    { required: true, message: '请输入电话', trigger: 'blur' },
                    { validator: validator.validatePhone, message: '请输入正确的电话', trigger: ['blur', 'change'] }
                ],
                fillDate:[
                    { validator: validatorFillDate, message: '当前日期已填报', trigger: ['blur'] }
                ],
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
});