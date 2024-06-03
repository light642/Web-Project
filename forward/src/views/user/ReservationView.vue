<template>
    <div class="ReservationView">
        <h1>预约情况</h1>
        <el-table :data="tableData">
            <el-table-column prop="roomId" label="录播室" width="100px" />
            <el-table-column prop="startTime" label="开始时间" width="200px" />
            <el-table-column prop="endTime" label="结束时间" width="200px" />
            <el-table-column label=" " width="80">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click.prevent="cancelReservation(scope.$index)">
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
    axios.get('/reservation').then((res) => {
        for (let i in res.data) tableData.value.push(res.data[i]);
    }).catch(relogin)
}
getTableData()

const cancelReservation = (index) => {
    axios.delete('/reservation/' + tableData.value[index].id).finally(getTableData);
}
</script>