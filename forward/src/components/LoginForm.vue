<template>
        <div v-if="activeForm === 'login'"  style="width: 300px;">
            <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="80px" class="login-form">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="loginForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="loginForm.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="" v-if="activeForm === 'login'" :class="{ active: activeForm === 'login' }"
                        @click="switchForm('register')">注册
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
        <div v-else-if="activeForm === 'register'">
            <el-form ref="registerForm" :model="registerForm" :rules="registerRules" label-width="80px"
                class="login-form">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="register">注册</el-button>
                    <el-button type="" v-if="activeForm === 'register'" :class="{ active: activeForm === 'register' }"
                        @click="switchForm('login')">登录
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
</template>

<script>
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
                    // 执行注册逻辑
                    this.$http.post("/user/register", this.registerForm).then((res) => {
                        console.log(res);
                        localStorage.setItem("userId", res.data.body.id);
                        localStorage.setItem("userRole", res.data.body.role);
                        if (res.data.code == 200) {
                            this.$router.push("/meetingRoom");
                            this.$message.success("注册并登录成功")
                        }
                    }).catch((err) => {
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
                    // 执行登录逻辑
                    this.$http.post("/user/login", this.loginForm).then((res) => {
                        if (res.data.code == 200) {
                            localStorage.setItem("userId", res.data.body.id);
                            localStorage.setItem("userRole", res.data.body.role);
                            this.$message.success("登录成功")
                            this.$router.push("/meetingRoom");
                        } else {
                            this.$message.error("用户名或密码错误")
                        }
                    }).catch((err) => {
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

.form-switch .el-button.active {
    color: #409eff;
}

.login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.login-form {
    height: 120px;
    width: 300px;
    padding: 20px;
}

.el-form-item .el-button {
    margin-left: 10px;
}

.form-switch .el-button.active {
    color: #409eff;
}

</style>