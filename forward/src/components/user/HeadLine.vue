<template>
    <div class="HeadLine">
        <el-menu mode="horizontal" :ellipsis="false" @select="handleSelect">
            <el-menu-item index="/home/user">个人</el-menu-item>
            <el-menu-item index="/home/reservation">预约一览</el-menu-item>
            <el-menu-item index="/home/room">录播室一览</el-menu-item>
            <div style="flex-grow: 1;" />
            <el-button v-if="true" @click.prevent="showLoginForm = !showLoginForm">登录</el-button>
            <el-button v-if="true" @click.prevent="router.push('/admin')">管理页</el-button>
        </el-menu>
    </div>
    <el-dialog v-model="showLoginForm" style="width: 400px; height: 250px;">
        <el-button @click.prevent="showRegisterForm=!showRegisterForm" style="width: 60px; height: 30px;">{{buttonText}}</el-button>
        <div  style="width: 400px; height: 20px;" />
        <login-form v-if="!showRegisterForm" @login-success="showLoginForm = !showLoginForm" />
        <register-form v-else @register-success="showLoginForm = !showLoginForm, showRegisterForm=!showRegisterForm" />

    </el-dialog>
</template>
<script setup>
import { computed, ref } from 'vue'
import router from '@/router';
import LoginForm from '@/components/LoginForm.vue';
import RegisterForm from './RegisterForm.vue';
import { showLoginForm } from '@/script/script';

const buttonText=computed(()=>{
    if(showRegisterForm.value) return "登录";
    return "注册"
})
const showRegisterForm = ref(false);
const handleSelect = (key) => {
    router.push(key)
}
</script>