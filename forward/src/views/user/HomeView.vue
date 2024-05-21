<template>
    <div class="home">
        <el-container>
            <el-header>
                <headLine />
            </el-header>
            <div style="width: 800px;margin: auto;">
                <el-main>
                    <RouterView />
                </el-main>
            </div>
        </el-container>
    </div>
</template>

<script setup>
import HeadLine from '@/components/user/HeadLine.vue';
</script>