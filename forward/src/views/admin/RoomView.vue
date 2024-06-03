<template>
    <div>
        <h2>录播室信息</h2>
        <div class="table-header">
            <el-button type="primary" @click.prevent="addDialogVisible = !addDialogVisible">新建会议室</el-button>
        </div>
        <el-table :data="tableData">
            <el-table-column prop="id" label="编号" width="100px" />
            <el-table-column prop="roomType" label="类型" width="100px" />
            <el-table-column label="操作">
                <template v-if="true" v-slot:default="scope">
                    <el-button type="danger" @click.prevent="onBlockClicked(scope.$index)">停用</el-button>
                    <el-button type="danger" @click.prevent="deleteMeetingRoom(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="新建会议室" v-model="addDialogVisible">
            <NewRoom @create-room-success="addDialogVisible = !addDialogVisible" />
        </el-dialog>
        <el-dialog title="选择起止日期" v-model="dateDialogVisible">
            <el-date-picker v-model="blockDate" type="daterange" value-format="YYYY-MM-DD" />
            <el-button @click.prevent="blockRoom()">停用</el-button>
        </el-dialog>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import NewRoom from '@/components/admin/NewRoom.vue';
import axios from 'axios';
import { relogin } from '@/script/script';

const tableData = ref([]);
function getTableData() {
    tableData.value = [];
    axios.get('/room').then((res) => {
        for (let i in res.data) tableData.value.push(res.data[i]);
    }).catch(relogin)
}
getTableData()


const addDialogVisible = ref(false);
const dateDialogVisible = ref(false);
const blockDate = ref('')
const blockParams = ref({
})
function onBlockClicked(index){
    blockParams.value.roomId = tableData.value[index].id;
    dateDialogVisible.value = !dateDialogVisible.value;
}

const blockRoom = () => {
    blockParams.value.startTime=blockDate.value[0];
    blockParams.value.endTime=blockDate.value[1];
    if(blockParams.value.startTime==undefined||blockParams.value.endTime==undefined){
        alert("选择起止日期");
        return;
    }
    axios.post('/block', blockParams.value).then(()=>{
        dateDialogVisible.value = !dateDialogVisible.value;
    }).catch(relogin);
}

const deleteMeetingRoom = (index) => {
    axios.delete('/room/' + tableData.value[index].id).catch(relogin).then(()=>{getTableData();}); 
}
</script>