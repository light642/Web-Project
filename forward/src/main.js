import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './mock'
//import axios from 'axios'



//axios.defaults.baseURL = 'https://localhost:8080';
createApp(App)
    .use(router)
    .use(ElementPlus)
    .mount('#app');
    