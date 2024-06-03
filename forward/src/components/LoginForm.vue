<template>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username" style="width: 200px;"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="form.password" style="width: 200px;"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click.prevent="login">登录</el-button>
        </el-form-item>
    </el-form>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'
import { showLoginForm } from '@/script/script'
const loginForm = ref()
const form = ref({
    username: '',
    password: ''
})
const rules = ref({
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
    ]
})

function login() {
    loginForm.value.validate((valid) => {
        if (valid) {
            axios.post("/session", form.value)
                .then(() => {
                    showLoginForm.value=!showLoginForm.value;
                })
                .catch((res) => {
                    alert(res)
                })
        } else {
            alert("请输入用户名和密码")
        }
    });
}
</script>