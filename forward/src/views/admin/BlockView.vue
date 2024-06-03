<template>
    <div>
        <el-table :data="tableData">
            <el-table-column prop="roomId" label="录播室编号" width="150px" />
            <el-table-column prop="startTime" label="开始时间" width="150px" />
            <el-table-column prop="endTime" label="结束时间" width="150px" />
            <el-table-column label=" " width="120">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click.prevent="cancelBlock(scope.$index)">
                        取消
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { relogin } from '@/script/script';

const tableData = ref([])
function getTableData() {
    tableData.value = [];
    axios.get('/blocks').then((res) => {
        for (let i in res.data) tableData.value.push(res.data[i]);
    }).catch(relogin)
}
getTableData()

const cancelBlock = (index) => {
    console.log(tableData.value[index].id)
    axios.delete('/block/' + tableData.value[index].id).catch(relogin);
    getTableData();
}
</script>