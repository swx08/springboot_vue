<template>
<div class="wrapper">
  <div style="margin: 200px auto; background-color: #fff; width: 400px; height: 450px; padding: 20px; border-radius: 10px">
    <div style="margin: 20px 0;text-align: center; font-size: 24px"><b>注册</b></div>
    <el-form :model="user" :rules="rules" ref="userForm">
      <el-form-item prop="username">
        <el-input v-model="user.username" placeholder="username" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="user.password" placeholder="password" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password></el-input>
      </el-form-item>
      <el-form-item prop="nickname">
        <el-input v-model="user.nickname" placeholder="nickname" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"></el-input>
      </el-form-item>
      <el-form-item prop="avatar">
        <el-input v-model="user.avatar" placeholder="avatar" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"></el-input>
      </el-form-item>
      <el-form-item style="margin: 10px 0; text-align: right">
        <el-button type="warning" size="small" autocomplete="off" @click="regist">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
export default {
  name: "Regist",
  data(){
    return{
      user:{},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在20个字符以内', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
        ],
        nickname: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在20个字符以内', trigger: 'blur'}
        ],
        avatar: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {max: 120, message: '长度在120个字符以内', trigger: 'blur'}
        ],
      }
    }
  },
  methods:{
    regist(){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {//表单校验
          this.request.post("/users/regist",this.user).then(res =>{
            if(res.statue === 200){
              this.$router.push("/login")
              this.$message.success("注册成功！")
            }else {
              this.$message.error(res.message)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
   .wrapper{
     height: 100vh;
     background-image: linear-gradient(to bottom right,#FC466B,#3F5EFB);
     overflow:hidden;
   }
</style>