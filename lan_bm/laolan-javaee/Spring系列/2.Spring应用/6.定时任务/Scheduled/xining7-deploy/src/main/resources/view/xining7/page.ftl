<!DOCTYPE html>
<html lang="en">
<head>
    <title>客运线路</title>
    <link rel="stylesheet" href="${mvcPath}/res/xining7/css/page.css">
</head>
<body>
<div id="app" v-cloak>
    pageStudents 方法 studentsPage,studentsPageBo 属性
    <div class="student-list">
        <el-table :data="studentsPage.list" border style="width: 100%">
            <el-table-column fixed prop="studentId" label="学号" width="150"></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="nation" label="民族" width="120"></el-table-column>
            <el-table-column prop="origin" label="籍贯" width="120"></el-table-column>
            <el-table-column prop="address" label="地址" width="300"></el-table-column>
            <el-table-column prop="sex" label="性别" width="120"></el-table-column>
            <el-table-column prop="gradeName" label="年级" width="120"></el-table-column>
            <el-table-column prop="className" label="班级" width="120"></el-table-column>
            <el-table-column prop="contactsPhone" label="联系电话" width="120"></el-table-column>
            <el-table-column prop="contactsName" label="联系人" width="120"></el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
    <div>
    <div class="student-page">
        <#-- @prev-click="studentsPrevClick"  @next-click="studentsNextClick" 可以忽略-->
        <el-pagination
                @current-change="studentsCurrentChange"
                <#--@prev-click="studentsPrevClick"
                @next-click="studentsNextClick"-->
                :current-page.sync="studentsPage.currentPage"
                :page-size="studentsPage.pageSize"
                layout="total, prev, pager, next, jumper"
                :total="studentsPage.totalCount">
        </el-pagination>
    </div>
</div>

<content tag="javascript">
    <script src="${mvcPath}/res/xining7/js/page.js"></script>
</content>
</body>
</html>