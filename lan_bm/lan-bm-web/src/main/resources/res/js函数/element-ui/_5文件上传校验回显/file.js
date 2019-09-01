var Main = new Vue({
    el: '#app',
    data() {
        return {
            upload: {
                imgAction: '../tsconfig.json',
                imgUrl: '',
                videoAction: '../tsconfig.json',
                videoUrl: '',
            },
        };
    },
    methods: {
        beforeUploadImg(file) {
            // 1.只能是图片 file.type: image/bmp,image/jpeg
            if(!(file.type.split('/')[0] == 'image')) {
                this.$message.error('只能上传图片');
                return false;
            }
            // 2. 图片类型
            // const isJPG = file.type === 'image/jpeg';

            // 3.文件大小 file.size
            if(!(file.size / 1024 / 1024 < 5)) {
                this.$message.error('上传图片大小不能超过 5MB!');
                return false;
            }

            return true;
        },
        // 这里只是摆设
        handleAvatarSuccessImg(res, file) {
            let self = this;
            const data = res;
            self.$message("模拟校验通过，回显，实际开发中代码已注释，请自己看代码");
            this.upload.imgUrl = URL.createObjectURL(file.raw); //图片回显
            // if (data.result == '0000') {
            //     self.$message(data.msg);
            //     this.formData.pictureUrl = data.data;       //将路径赋值给po
            //     this.upload.imgUrl = URL.createObjectURL(file.raw); //图片回显
            // } else if (data.result == '9999') {
            //     self.$message(data.msg);
            // }

        },
        beforeUploadVideo(file) {
            // 1.只能是视频 file.type: video/mp4,
            if(!(file.type.split('/')[0] == 'video')) {
                this.$message.error('只能上传视频');
                return false;
            }
            // 2. 视频类型
            // const isJPG = file.type === 'image/jpeg';

            // 3.文件大小 file.size
            if(!(file.size / 1024 / 1024 < 100)) {
                this.$message.error('上传文件大小不超过 100 MB!');
                return false;
            }

            return true;
        },
        handleAvatarSuccessVideo(res, file) {
            let self = this;
            const data = res;
            self.$message("模拟校验通过，回显，实际开发中代码已注释，请自己看代码");
            this.upload.videoUrl = URL.createObjectURL(file.raw); //图片回显
            // if (data.result == '0000') {
            //     self.$message(data.msg);
            //     this.formData.pictureUrl = data.data;       //将路径赋值给po
            //     this.upload.videoUrl = URL.createObjectURL(file.raw); //图片回显
            // } else if (data.result == '9999') {
            //     self.$message(data.msg);
            // }

        },
    }
});