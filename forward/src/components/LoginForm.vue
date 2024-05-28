<template>
    <div class="login-container">
      <div v-if="activeForm === 'login'" class="form-card">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="80px" class="login-form">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login" class="full-width">登录</el-button>
            <el-button @click="switchForm('register')" class="full-width" plain>注册</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div v-else-if="activeForm === 'register'" class="form-card">
        <el-form ref="registerForm" :model="registerForm" :rules="registerRules" label-width="80px" class="login-form">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="registerForm.username" placeholder="请输入用户名" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="registerForm.password" placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="register" class="full-width">注册</el-button>
            <el-button @click="switchForm('login')" class="full-width" plain>登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        activeForm: 'login',
        loginForm: {
          username: '',
          password: ''
        },
        loginRules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        },
        registerForm: {
          username: '',
          password: '',
        },
        registerRules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      switchForm(formName) {
        this.activeForm = formName;
      },
      register() {
        this.$refs.registerForm.validate((valid) => {
          if (valid) {
            axios.post("/user/register", this.registerForm, { withCredentials: true })
              .then((res) => {
                if (res.data.code == 200) {
                  this.$router.push("/meetingRoom");
                  this.$message.success("注册并登录成功");
                }
              })
              .catch((err) => {
                console.log(err);
              });
          } else {
            console.log('注册失败');
            return false;
          }
        });
      },
      login() {
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            axios.post("/user/login", this.loginForm, { withCredentials: true })
              .then((res) => {
                if (res.data.code == 200) {
                  this.$message.success("登录成功");
                  this.$router.push("/meetingRoom");
                } else {
                  this.$message.error("用户名或密码错误");
                }
              })
              .catch((err) => {
                console.log(err);
              });
          } else {
            console.log('登录失败');
            return false;
          }
        });
      }
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f5f5;
  }
  
  .form-card {
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    width: 400px;
  }
  
  .login-form {
    margin-top: 20px;
  }
  
  .el-form-item {
    margin-bottom: 20px;
  }
  
  .full-width {
    width: 100%;
  }
  
  .el-button {
    margin-top: 10px;
  }
  
  .el-input__inner {
    height: 40px;
  }
  
  .el-input__inner:focus {
    border-color: #409eff;
  }
  
  .el-button--primary {
    background-color: #409eff;
    border-color: #409eff;
    color: #fff;
  }
  
  .el-button--primary:hover {
    background-color: #66b1ff;
    border-color: #66b1ff;
  }
  
  .el-button[plain] {
    color: #409eff;
    border-color: #dcdfe6;
  }
  </style>
  