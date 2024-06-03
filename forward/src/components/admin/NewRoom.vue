<template>
    <el-form :model="newRoom">
        <el-form-item label="图片" prop="name">
            <el-upload :http-request="addImg" multiple :show-file-list="true" list-type="picture-card">
                <el-icon>
                    <Plus />
                </el-icon>
            </el-upload>
        </el-form-item>
        <el-form-item label="编号" prop="id">
            <el-input v-model="newRoom.id" />
        </el-form-item>
        <el-form-item label="类型" prop="roomType">
            <el-input v-model="newRoom.roomType" />
        </el-form-item>
    </el-form>
    <div class="dialog-footer">
        <el-button type="primary" :loading="addDialogLoading" @click="addMeetingRoom">确定</el-button>
    </div>
</template>
<script setup>
import { relogin } from '@/script/script';
import axios from 'axios';
import { ref } from 'vue';

const addDialogLoading = ref(false);
const newRoom = ref({
    id: "",
    roomType: ""
});
const imgList = ref([])
const addImg = (option) => {
    imgList.value.push(option)
}
const emit=defineEmits(['createRoomSuccess'])
async function addMeetingRoom() {
    let dataForm = new FormData();
    dataForm.append('id', newRoom.value.id);
    dataForm.append('roomType', newRoom.value.roomType);

    imgList.value.forEach((it) => {
        dataForm.append('file', it.file)
    })
    await axios.post('/room', dataForm,
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
    ).then(() => {
        emit("createRoomSuccess")
    }).catch(relogin)
    
}
</script>