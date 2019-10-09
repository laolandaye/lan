<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<head>
    <title>首页</title>
    <style>
        .editDialog .el-dialog__header {
            display: none !important;
        }
        .editDialog .el-dialog__body {
            padding: 5px !important;
        }
        .el-form.el-form--inline {
            width: 100%;
        }
        .avatar-uploader .el-upload {
            border: 1px dashed #d9d9d9;
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
        }
        .avatar-uploader .el-upload:hover {
            border-color: #409EFF;
        }
        .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            line-height: 178px;
            text-align: center;
        }
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }
    </style>
</head>
<body>
<div id="app">
    <hr />
    单个图片上传
    <el-upload
            class="avatar-uploader"
            :action="upload.imgAction"
            :show-file-list="false"
            :on-success="handleAvatarSuccessImg"
            :before-upload="beforeUploadImg">
        <img v-if="upload.imgUrl" :src="upload.imgUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <hr />
    单个视频上传
    <el-upload
            class="upload-demo"
            drag
            :action="upload.videoAction"
            :show-file-list="false"
            :on-success="handleAvatarSuccessVideo"
            :before-upload="beforeUploadVideo">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
    <video v-if="upload.videoUrl" :src="upload.videoUrl" controls="controls"></video>

</div>
<content tag="javascript">
<script>
    let vue = new Vue({
        el: '#app',
        data() {
            return {
                upload: {
                    imgAction: '',
                    imgUrl: '',
                    videoAction: '',
                    videoUrl: '',
                }
            }
        },
        beforeCreate: function () {
            this.contextPath = '';
        },
        created: function () {
            let self = this;
            this.getContextPath();
            self.upload.imgAction = self.contextPath + "/rest/api/upload";
            self.upload.videoAction = self.contextPath + "/rest/api/upload";
        },
        methods: {
            getContextPath: function () {
                var contextPath = document.location.pathname;
                var contextPath = contextPath.split('/')[1];
                var contextPath = "/" + contextPath;
                this.contextPath = contextPath;
            },
            beforeUploadImg(file) {
                // const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                // if (!isJPG) {
                //     this.$message.error('上传头像图片只能是 JPG 格式!');
                // }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                // return isJPG && isLt2M;
                return isLt2M;
            },
            handleAvatarSuccessImg(res, file) {
                let self = this;
                const data = res;
                if (data.result == '0000') {
                    self.$message(data.msg);
                    this.upload.imgUrl = URL.createObjectURL(file.raw); //图片回显
                } else if (data.result == '9999') {
                    self.$message(data.msg);
                }

            },
            beforeUploadVideo(file) {
                // const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                // if (!isJPG) {
                //     this.$message.error('上传头像图片只能是 JPG 格式!');
                // }
                // if (!isLt2M) {
                //     this.$message.error('上传头像图片大小不能超过 2MB!');
                // }
                // return isJPG && isLt2M;
                return true;
            },
            handleAvatarSuccessVideo(res, file) {
                let self = this;
                const data = res;
                if (data.result == '0000') {
                    self.$message(data.msg);
                    this.upload.videoUrl = URL.createObjectURL(file.raw); //图片回显
                } else if (data.result == '9999') {
                    self.$message(data.msg);
                }

            },
        }

    });

</script>
</content>
</body>
</html>
