<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column label="录播室编号" prop="roomId"></el-table-column>
            <el-table-column label="房间类型" prop="room_type"></el-table-column>
            <el-table-column label="位置" prop="location"></el-table-column>
            <el-table-column label="开始时间" prop="start"></el-table-column>
            <el-table-column label="结束时间" prop="end"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios'
import VueCookie from 'vue-cookie';

const tableData = ref([])
axios.get('/room/id').then((res)=>{
    for(let i in res.data) tableData.value.push(res.data[i]);
})


</script>
