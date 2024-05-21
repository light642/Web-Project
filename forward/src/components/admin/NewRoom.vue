<template>
        <el-form :model="newMeetingRoom" ref="addForm" :rules="addFormRules" label-width="80px">
            <el-form-item label="图片" prop="name">
                <el-input v-model="newMeetingRoom.name" />
            </el-form-item>
            <el-form-item label="会议室名称" prop="name">
                <el-input v-model="newMeetingRoom.name" />
            </el-form-item>
            <el-form-item label="类型" prop="capacity">
                <el-input-number v-model="newMeetingRoom.capacity" :min="1" />
            </el-form-item>
            <el-form-item label="位置" prop="name">
                <el-input v-model="newMeetingRoom.name" />
            </el-form-item>
        </el-form>
        <div class="dialog-footer">
            <el-button type="primary" :loading="addDialogLoading" @click="addMeetingRoom,$emit('submit')">确定</el-button>
        </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue';

const addDialogLoading = ref(false);
const newMeetingRoom = ref({
    name: '',
    capacity: null
});
const addFormRules = ref({
    name: [
        { required: true, message: '请输入会议室名称', trigger: 'blur' }
    ],
    capacity: [
        { required: true, message: '请输入容纳人数', trigger: 'blur' },
        { type: 'number', message: '请输入有效的数字', trigger: 'blur' }
    ]
});
const addForm=ref(null)
const addMeetingRoom = () => {
    addForm.value.validate((valid) => {
        if (valid) {
            addDialogLoading.value = true;
            // 模拟异步请求
            axios.post("/meetingRoom/createMeetingRoom/", newMeetingRoom.value).then((res) => {
                console.log(res.data.code)
                console.log(res.data.code == 200)

                if (res.data.code === 200) {
                    this.$message.success("添加会议室成功")
                    //getMeetingRoomList()
                }
            }).catch((err) => {
                console.log(err)
                this.$message.error("内部异常，添加失败")
            });
            addDialogLoading.value = false;
            //getMeetingRoomList()
        }
    });
};
</script>