import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import VueCookies from 'vue-cookies'



axios.defaults.baseURL = 'http://localhost:8089';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.withCredentials=true;
createApp(App)
    .use(router)
    .use(ElementPlus)
    .use(VueCookies)
    .mount('#app');
    