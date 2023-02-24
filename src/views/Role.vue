<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" placeholder="请输入角色名" suffix-icon="el-icon-search" v-model="name"></el-input>
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
  </div>
  <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="80"></el-table-column>
    <el-table-column prop="name" label="名称"></el-table-column>
    <el-table-column prop="description" label="描述"></el-table-column>
    <el-table-column label="操作" width="280" align="center">
      <template  slot-scope="scope">
        <el-button type="info" @click="selectMenu(scope.row.id)">分配菜单<i class="el-icon-menu"></i></el-button>
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
  <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="27%">
    <el-form label-width="80px" size="small">
      <el-form-item label="名称">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.description" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>

  <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="27%">
    <el-tree
        :props="props"
        :data="menuData"
        show-checkbox
        ref="tree"
        node-key="id"
        :default-expanded-keys="expends"
        :default-checked-keys="checks">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span><i :class="data.icon"></i> {{ data.name }}</span>
        </span>
    </el-tree>
    <div slot="footer" class="dialog-footer">
      <el-button @click="menuDialogVis = false">取 消</el-button>
      <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "Role",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      form:{},
      multipleSelection:[],
      dialogFormVisible:false,
      menuDialogVis: false,
      menuData:[],
      props:{
        label: 'name'
      },
      expends:[],
      checks:[],
      roleId:0
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      //使用axios进行请求
      this.request.get("/Roles/page",{
        params:{//参数
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
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
      this.name=""
      this.load()
    },
    save(){
      this.request.post("/Roles",this.form).then(res=>{
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
    saveRoleMenu(){
      this.request.post("/roleMenu/"+this.roleId,this.$refs.tree.getCheckedKeys()).then(res =>{
        if(res.statue == 200){
          this.$message.success("绑定成功")
          this.load()
          this.menuDialogVis = false
        }else {
          this.$message.error(res.message)
          this.menuDialogVis = false
        }
      })
    },
    handleDelete(id){
      this.request.delete("/Roles/"+id).then(res=>{
        if(res.statue == 200){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })

      this.request.delete("/roleMenu/"+id)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/Roles/batch",ids).then(res=>{
        if(res.statue == 200){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })

      this.request.delete("/roleMenu/"+this.roleId)
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
    },
    selectMenu(roleId){
      this.menuDialogVis = true
      this.roleId = roleId
      this.request.get("/menus").then(res=>{//后台响应数据
        this.menuData = res.data
        this.expends = this.menuData.map(v => v.id)
      })

      //回显被选中的菜单数据
      this.request.get("/roleMenu/"+this.roleId).then(res =>{
        this.checks = res.data
      })
    }
  }
}
</script>

<style>
.el-table th{
  background-color: #eee !important;
}
</style>