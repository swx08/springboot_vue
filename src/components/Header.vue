<template>
<div style="line-height: 60px; display: flex">
  <div style="flex: 1;">
    <span :class="collapseBtnClass" style=" font-size: 18px; cursor: pointer" @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      </el-breadcrumb>
  </div>
  <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
    <div style="display: inline-block" >
      <img :src="user.avatar" alt=""
            style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
      <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
    </div>
    <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
      <el-dropdown-item style="font-size: 14px; padding: 5px 0">
        <router-link to="/personal">修改个人信息</router-link>
      </el-dropdown-item>
      <el-dropdown-item style="font-size: 14px; padding: 5px 0">
        <span style="text-decoration: none" @click="login">登录</span>
      </el-dropdown-item>
      <el-dropdown-item style="font-size: 14px; padding: 5px 0">
        <span style="text-decoration: none" @click="loginOut">退出</span>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  props:{
    collapseBtnClass:String,
    collapse:Boolean
  },
  methods:{
    loginOut(){
      this.$router.push("/login")
      localStorage.removeItem("user")//删除浏览器中保存的数据
      this.$message.success("退出成功！")
    },
    login(){
      this.$router.push("/login")
      localStorage.removeItem("user")//删除浏览器中保存的数据
      this.$message.success("前往登录！")
    }
  }
}
</script>

<style scoped>

</style>