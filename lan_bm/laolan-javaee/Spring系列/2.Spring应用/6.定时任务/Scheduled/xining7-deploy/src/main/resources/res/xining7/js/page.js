"use strict";
var appVm = new Vue({
    el: '#app',
    data: function () {
        return {
            studentsPage: {
                list: [],
                currentPage: 1,
                pageSize: 6,
                totalCount: 0,
            },
        }
    },
    computed: {
    },
    watch: {
    },
    created: function () {
        let self = this;
        this.getContextPath();
    },
    mounted: function () {
        let self = this;
        this.pageStudents();
    },
    beforeCreate: function () {
        let self = this;
        this.contextPath = "";
    },
    methods: {
        getContextPath: function () {
            let local = document.location;
            let contextPath = "/" + local.pathname.split('/')[1];
            let basePath = local.protocol + "//" + local.host + "/" + contextPath;
            this.contextPath = contextPath; //项目路径
        },

        handleClick(row) {
            console.log(row);
        },
        //点击页码一，或者改变页面（currentPage 改变时会触发）
        studentsCurrentChange(val) {
            let self = this;
            this.studentsPage.currentPage = val;
            this.pageStudents();
        },
        //点击上一页
        studentsPrevClick(val) {
            let self = this;
            this.studentsPage.currentPage = val;
            this.pageStudents();
        },
        //点击下一页
        studentsNextClick(val) {
            let self = this;
            this.studentsPage.currentPage = val;
            this.pageStudents();
        },
        /* ajax */
        // 2.条件分页数据
        pageStudents: function () {
            let self = this;
            axios.post(this.contextPath + "/api/pageStudents",
                self.studentsPage
            ).then(function(result) {
                    const data = result.data;
                    self.studentsPage = data;
                })

        },
    },
});