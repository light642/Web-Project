<template>
    <div>
        <h2>录播室信息</h2>
        <div class="table-header">
            <el-button type="primary" @click.prevent="addDialogVisible = !addDialogVisible">新建会议室</el-button>
        </div>
        <el-table :data="tableData">
            <el-table-column prop="id" label="编号" width="100px"/>
            <el-table-column prop="roomType" label="类型" width="100px"/>
            <el-table-column prop="location" label="位置" width="100px" />
            <el-table-column label="操作">
                <template v-if="true" v-slot:default="scope">
                    <el-button type="danger" @click=blockRoom(scope.$index)>停用</el-button>
                    <el-button type="danger" @click="deleteMeetingRoom(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="新建会议室" v-model="addDialogVisible">
            <NewRoom @submit="addDialogVisible = !addDialogVisible" />
        </el-dialog>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import NewRoom from '@/components/admin/NewRoom.vue';
import axios from 'axios';
//import VueCookie from 'vue-cookie';

let username = ''//VueCookie.get("username")
/*********/
username="admin1"
/*********/
const tableData = ref([]);
axios.get("/room").then(res => {
    for (let i in res.data) tableData.value.push(res.data[i]);
})


const addDialogVisible = ref(false);

const blockRoom = (index) => {
    const startTime = new Date("2021-01-01");
    const endTime = new Date("2022-01-01");
    const params={
        room_id: tableData.value[index].id,
        username: username,
        startTime: startTime,
        endTime: endTime
    }
    axios.post('/block'+params);
}


const deleteMeetingRoom = (index) => {
    const params = {id: tableData.value[index].id}
    axios.delete('/room/'+params);
    tableData.value=[];
    axios.get('/room').then((res)=>{
        for(let i in res.data) tableData.value.push(res.data[i]);
    })
}
</script>