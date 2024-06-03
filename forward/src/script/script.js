import {ref} from "vue"
const showLoginForm=ref(false)
function relogin(res){
    console.log("relogin")
    if(res.response.status==401) {
        showLoginForm.value = true;
    }
    else if(res.response.status==403){
        alert("权限不足，请更换账号")
    }
}
export  { showLoginForm, relogin}