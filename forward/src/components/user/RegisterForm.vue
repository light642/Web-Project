<template>
    <el-form ref="registerForm" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username" style="width: 200px;"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="form.password" style="width: 200px;"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click.prevent="register">注册</el-button>
        </el-form-item>
    </el-form>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';

const registerForm = ref()
const form = ref({
    username: '',
    password: '',
})
const rules = ref({
    username: [
        { required: true, trigger: 'blur' }
    ],
    password: [
        { required: true, trigger: 'blur' }
    ]
})

const emit=defineEmits(['registerSuccess'])
function register() {
    registerForm.value.validate((valid) => {
        if (valid) {
            axios.post("/user", form.value).then(()=>{
                emit('registerSuccess')
            }).catch((res)=>{
                if(res.response.status==403) alert("用户名重复，请重试")
            })
        } else {
            alert('请输入用户名和密码');
        }
    });
}
</script>