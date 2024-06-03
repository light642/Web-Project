<template>
    <div>
        <el-image style="width: 200px; height: 200px" :src="getUrl()" />
        <h1>编号：{{ props.room.id }}</h1>
        <h1>类型：{{ props.room.roomType }}</h1>
        <el-date-picker v-model="dateSelected" unlink-panels type="daterange" range-separator="到"
            start-placeholder="开始时间" end-placeholder="结束时间" value-format="YYYY-MM-DD" :disabled-date="disabledDate" />
        <el-button @click.prevent="reserve">预约</el-button>
    </div>
</template>
<script setup>
import { relogin } from '@/script/script';
import axios from 'axios';
import { defineProps, ref } from 'vue';
const props = defineProps(['room']);
const getUrl = () => {
    return "http://localhost:8089/images/" + props.room.id + ".png"
}

const dateSelected = ref('')

const emit = defineEmits(['reserveSuccess'])
const reserve = () => {
    const reservation = {
        roomId: props.room.id,
        startTime: dateSelected.value[0],
        endTime: dateSelected.value[1]
    }
    axios.post("/reservation", reservation).then((res) => {
        if(res.data) emit("reserveSuccess") ;
        else{
            alert("房间的部分所选时段已被预约")
        }
    }).catch(relogin);
}
</script>