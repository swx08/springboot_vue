<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="username"></el-input>
    <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-map-location" class="ml-5" v-model="address"></el-input>
    <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    <el-button type="warning" @click="reset">重置</el-button>
  </div>
  <div style="margin: 10px 0">
    <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i> </el-button>
    <el-popconfirm
        class="ml-5"
        confirm-button-text='确定'
        cancel-button-text='我再想想'
        icon="el-icon-info"
        icon-color="red"
        title="您确定删除吗？"
        @confirm="deleteBatch"
    >
      <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove"></i> </el-button>
    </el-popconfirm>
    <el-upload
        class="upload-demo"
        action="http://localhost:9090/users/import" :show-file-list="false" accept=".xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
      <el-button type="primary">导入<i class="el-icon-download"></i> </el-button>
    </el-upload>
    <el-button type="primary" @click="handleExportExcel" class="ml-5">导出<i class="el-icon-upload2"></i> </el-button>
  </div>
  <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="80"></el-table-column>
    <el-table-column prop="username" label="用户名" width="140"></el-table-column>
    <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
    <el-table-column prop="email" label="邮箱"></el-table-column>
    <el-table-column prop="phone" label="电话"></el-table-column>
    <el-table-column prop="address" label="地址"></el-table-column>
    <el-table-column label="操作" width="200" align="center">
      <template  slot-scope="scope">
        <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我再想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="handleDelete(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除<i class="el-icon-remove"></i></el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <div style="padding: 10px 0" align="center">
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 15]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
  <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="27%">
    <el-form label-width="80px" size="small">
      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="头像">
        <el-input v-model="form.avatar" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>

</div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      username: "",
      address: "",
      form:{},
      multipleSelection:[],
      dialogFormVisible:false
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      //使用axios进行请求
      this.request.get("/users/page",{
        params:{//参数
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          address: this.address
        }
      }).then(res=>{//后台响应数据
        this.tableData = res.data.data
        this.total = res.data.total
      })
    },
    //请求分页查询数据
    //   fetch("http://localhost:9090/users/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username+"&address="+this.address).then(res=>res.json()).then(res=>{
    //     this.tableData = res.data
    //     this.total = res.total
    //   })
    // },
    reset(){
      this.username=""
      this.address=""
      this.load()
    },
    save(){
      this.request.post("/users",this.form).then(res=>{
        if(res.statue == 200){
          this.$message.success("保存成功")
          this.load()
          this.dialogFormVisible = false
        }else {
          this.$message.error("保存失败")
          this.dialogFormVisible = false
        }
      })
    },
    handleExcelImportSuccess(){
      this.$message.success("导入成功!")
      this.load()
    },
    handleExportExcel(){
      window.open("http://localhost:9090/users/export")
    },
    handleDelete(id){
      this.request.delete("/users/"+id).then(res=>{
        if(res.statue == 200){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/users/batch",ids).then(res=>{
        if(res.statue == 200){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style>
.el-table th{
  background-color: #eee !important;
}
</style>