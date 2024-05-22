<template>
    <div class="UserInfoView">
        <el-card>
            <template #header>
                <div class="card-header">
                    <span>通知</span>
                </div>
            </template>
            <li v-for="i in info" :key="i.key">{{ i }}</li>
        </el-card>
        <div style="height: 50px;"></div>
        <el-button @click="logout">退出登录</el-button>
    </div>
</template>
<script setup>
import axios from 'axios';
import {ref} from 'vue'
//import VueCookie from 'vue-cookie';

let username = ''//VueCookie.get("username")
/*********/
username="user1"
/*********/

const info = ref([])
axios.get('/message/'+username).then((res)=>{
    for(let i in res.data) info.value.push(res.data[i]);
})

const logout = ()=>{
    alert("logout clicked")
    axios.delete('/session',username)
}
</script>