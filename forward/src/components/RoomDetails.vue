<template>
    <div>
        <h1>{{props.room.img}}</h1>
        <h1>类型：{{props.room.roomType}}</h1>
        <h1>位置：{{props.room.location}}</h1>
        <el-button>预约</el-button>
    </div>
</template>
<script setup>
import {defineProps} from 'vue';
const props = defineProps(['room']);
</script>