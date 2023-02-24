<template>
<el-card style="width: 500px; background-color: pink">
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
      <el-form-item label="头像链接">
        <el-input v-model="form.avatar" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="update">确 定</el-button>
      </el-form-item>
    </el-form>
</el-card>
</template>

<script>
export default {
  name: "Personal",
  data(){
    return{
      form:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    //回显此时登录的用户信息
    this.request.get("/users/"+this.user.username).then(res => {
      if(res.statue == 200){
        this.form = res.data;
      }
    })
  },
  methods: {
    update() {
      this.request.post("/users", this.form).then(res => {
        if (res.statue == 200) {
          this.$message.success("个人信息已被修改，需重新登录！")
          this.$router.push("/login")
          localStorage.removeItem("user")//删除浏览器中保存的数据
        } else {
          this.$message.error(res.message)
        }
      })
    }
  }
}
</script>
