<template>
    <div>
        <el-table :data="tableData">
            <el-table-column prop="roomId" label="录播室编号" width="150px" />
            <el-table-column prop="location" label="位置" width="150px" />
            <el-table-column prop="start" label="开始时间" width="150px" />
            <el-table-column prop="end" label="结束时间" width="150px" />
            <el-table-column label=" " width="120">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click.prevent="cancelRes(scope.$index)">
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
import VueCookie from 'vue-cookie';

const username = VueCookie.get("username")
/*********/
username="admin1"
/*********/
const tableData = ref([])
axios.get('/blocks').then((res)=>{
    for(let i in res.data) tableData.value.push(res.data[i]);
})


const cancelRes = (index) => {
    alert("delete room " + index);
    //const params = {id: tableData.value[index].id}
    //axios.delete('/block/'+params);
    //tableData.value=[];
    //axios.get('/blocks').then((res)=>{
    //    for(let i in res.data) tableData.value.push(res.data[i]);
    //})
}
</script>