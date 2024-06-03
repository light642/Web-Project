<template>
    <div class="RoomView">
        <el-container>
            <el-header>
                <el-select clearable v-model="typeFilter" placeholder="Select" style="width: 155px">
                    <el-option v-for="item in options" :key="item.key" :label="item" :value="item" />
                </el-select>
                <el-date-picker v-model="dateSelected" type="daterange" range-separator="到" start-placeholder="开始时间"
                    end-placeholder="结束时间" value-format="YYYY-MM-DD" />
                <el-button @click="filter">筛选</el-button>
            </el-header>
            <el-main>
                <el-space wrap>
                    <el-card shadow="hover" v-for="(room, index) in currentRoomList" :key="room.id"
                        @click.prevent="showDetails(index)" class="box-card">
                        <div><el-image style="width: 100px; height: 100px" :src="getUrl(room)" /></div>
                        <span>{{ room.roomType }}</span>
                    </el-card>
                </el-space>
            </el-main>
            <el-footer>
                <el-pagination background layout="prev, pager, next" 
                v-model:current-page="currentPage" 
                @current-change="pageTo" 
                :page-size='16'
                :total='roomData.length'
                />
            </el-footer>

            <el-dialog v-model="showRoomDetails.visable" style="max-width: 450px;">
                <room-details :room="roomData[showRoomDetails.index]" @reserve-success="showRoomDetails.visable=!showRoomDetails.visable" style="max-width: 400px;"/>
            </el-dialog>
        </el-container>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import RoomDetails from '@/components/RoomDetails.vue';
import axios from 'axios';
import { relogin } from '@/script/script';

const currentPage=ref(1)
const pageTo=()=>{
    currentRoomList.value=[]
    let start=16*(currentPage.value-1);
    let end=(start+16>roomData.value.length) ? roomData.value.length : (start+16);
    while(start!=end&&roomData.value[start]!=null){
        currentRoomList.value.push(roomData.value[start++]);
    }
}
const getUrl=(room)=>{
    if(room==null) return null;
    return "http://localhost:8089/images/"+room.id+".png"
}
const roomData = ref([])
const filterParams = ref({})
const currentRoomList=ref([])
const refreshRoomData = () => {
    roomData.value=[];
    axios.get('/room',{ params:filterParams.value }).then((res) => {
        for (let i in res.data) roomData.value.push(res.data[i]);
        currentPage.value=1;
        pageTo();
    }).catch(relogin)
}
refreshRoomData();

const typeFilter = ref('');
const options = ref([
    'type1', 'type2', 'type3'
]);
const dateSelected = ref([]);
const filter = () => {
    filterParams.value = {
        roomType: typeFilter.value,
        startTime: dateSelected.value ? dateSelected.value[0] : null,
        endTime: dateSelected.value ? dateSelected.value[1] : null
    };
    refreshRoomData();
}
const showRoomDetails = ref({
    visable: false,
    index: ''
})
const showDetails = (index) => {
    showRoomDetails.value.visable = true;
    showRoomDetails.value.index = index+currentPage.value*16-16;
}
</script>