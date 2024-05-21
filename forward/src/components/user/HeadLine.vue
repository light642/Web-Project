<template>
    <div class="HeadLine">
        <el-menu :default-active="activeIndex" mode="horizontal" :ellipsis="false" @select="handleSelect">
            <el-menu-item index="/home/user">个人</el-menu-item>
            <el-menu-item index="/home/reservation">预约一览</el-menu-item>
            <el-menu-item index="/home/room">录播室一览</el-menu-item>
            <div style="flex-grow: 1;" />
            <el-button v-if="true" @click.prevent="showLoginForm=!showLoginForm">
                登录（调试）
            </el-button>
            <el-button v-if="true" @click.prevent="router.push('/admin')">
                管理员（调试）
            </el-button>
        </el-menu>
    </div>
    <el-dialog align-center v-model="showLoginForm" width="400">
        <login-form style="width: 400px;" />
    </el-dialog>
</template>
<script setup>
import { ref } from 'vue'
import router from '@/router';
import LoginForm from '@/components/LoginForm.vue';

const showLoginForm=ref(false);

const activeIndex = ref('1')
const handleSelect = (key) => {
    router.push(key)
}
</script>
<style>
.FxCenter{
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 2;/*低于2时，会被element表格覆盖*/
}
</style>