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
                        <div style="height: 100px;width: 100px;background-color: aqua;">img</div>
                        {{ room.roomType }}
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

            <el-dialog v-model="showRoomDetails.visable">
                <room-details :room="roomData[showRoomDetails.index]" />
            </el-dialog>
        </el-container>
    </div>
</template>
<script setup>
let test = false;
//test = true;
import { ref } from 'vue';
import RoomDetails from '@/components/RoomDetails.vue';
import axios from 'axios';

//页面初始化
const currentPage=ref(1)
const pageTo=()=>{
    currentRoomList.value=[]
    let start=16*(currentPage.value-1);
    let end=(start+16>roomData.value.length) ? roomData.value.length : (start+16);
    console.log(start+' '+end);
    while(start!=end&&roomData.value[start]!=null){
        currentRoomList.value.push(roomData.value[start++]);
    }
    console.log(currentRoomList.value)
}

//初始化房间列表
const roomData = ref([])
const currentRoomList=ref([])
const refreshRoomData = () => {
    roomData.value=[];
    axios.get('/room/user').then((res) => {
        for (let i in res.data) roomData.value.push(res.data[i]);
        
        currentPage.value=1;
        pageTo();
    })

}
refreshRoomData();

//筛选器
const typeFilter = ref('');
const options = ref([
    'option1', 'option2', 'option3'
]);
const dateSelected = ref([]);
const filterParams = ref({});
const filter = () => {
    if (test == true) { alert('filter clicked!'); return }

    filterParams.value = {
        type: typeFilter.value,
        start: dateSelected.value ? dateSelected.value[0] : null,
        end: dateSelected.value ? dateSelected.value[1] : null
    };
    axios.post('room/user/filter', filterParams.value).then(() => {
        refreshRoomData();
    });
}

//房间详情
const showRoomDetails = ref({
    visable: false,
    index: ''
})
const showDetails = (index) => {
    showRoomDetails.value.visable = true;
    showRoomDetails.value.index = index+currentPage.value*16-16;
}


</script>