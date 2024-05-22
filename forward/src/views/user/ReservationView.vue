<template>
    <div class="ReservationView">
        <h1>预约情况</h1>
        <el-table :data="tableData">
            <el-table-column prop="roomId" label="录播室" width="70px" />
            <el-table-column prop="roomId" label="类型" width="70px" />
            <el-table-column prop="roomId" label="地点" width="100px" />
            <el-table-column prop="start" label="开始时间" width="100px" />
            <el-table-column prop="end" label="结束时间" width="100px" />
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
//import VueCookie from 'vue-cookie';

let username = ""//VueCookie.get("username")
/*********/
username="user1"
/*********/

const tableData = ref([])
axios.get('/reservation/'+username).then((res)=>{
    for(let i in res.data) tableData.value.push(res.data[i]);
})

const cancelReservation = (index) => {
    axios.delete('/reservation/' + tableData.value[index].id);
    tableData.value=[];
    axios.get('/reservation/'+username).then((res)=>{
        for(let i in res.data) tableData.value.push(res.data[i]);
    })
}
</script>