<template>
    <div>
        <el-row class="tac">
            <el-col :span="24">
                <div style="height: 50px;">
                    <h1>
                        <span>管理页</span>
                        <el-button v-if="true" @click.prevent="showLoginForm = !showLoginForm">登录</el-button>
                        <el-button v-if="true" @click.prevent="router.push('/')">用户</el-button>
                    </h1>
                </div>
                <el-menu @select="handleOpen" mode="vertical">
                    <el-menu-item index="/admin/room">录播室</el-menu-item>
                    <el-menu-item index="/admin/block">停用安排</el-menu-item>
                </el-menu>
            </el-col>
        </el-row>
        <el-dialog align-center v-model="showLoginForm" width="400">
            <login-form style="width: 400px;" />
        </el-dialog>
    </div>
</template>

<script setup>
import router from '@/router';
import { showLoginForm } from '@/script/script';
import LoginForm from '../LoginForm.vue';

const handleOpen = (key) => {
    router.push(key)
}
</script>