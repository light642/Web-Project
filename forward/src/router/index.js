import AdminView from "@/views/admin/AdminView.vue";
import BlockView from "@/views/admin/BlockView.vue"
import AdminRoomView from "@/views/admin/RoomView.vue";

import HomeView from "@/views/user/HomeView.vue";
import ReservationView from "@/views/user/ReservationView.vue";
import RoomView from "@/views/user/RoomView.vue";
import UserInfoView from "@/views/user/UserInfoView.vue";

import { createWebHashHistory, createRouter } from "vue-router"

const routes = [
    { path: '/', redirect: '/home' },
    {
        path: '/home',
        component: HomeView,
        children: [
            { path: 'user', component: UserInfoView },
            { path: 'reservation', component: ReservationView },
            { path: 'room', component: RoomView }
        ] 
    },
    {
        path: '/admin',
        component: AdminView,
        children: [
            { path: 'block', component: BlockView },
            { path: 'room', component: AdminRoomView }
        ]
    }
];
const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export default router;
