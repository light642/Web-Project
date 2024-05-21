<template>
    <div class="AdminView">
        <el-container>
            <el-aside>
                <SideMenu />
            </el-aside>
            <el-main>
                <RouterView />
            </el-main>
        </el-container>
    </div>
</template>
<script setup>
import SideMenu from '@/components/admin/SideMenu.vue'
</script>