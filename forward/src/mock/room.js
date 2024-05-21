const room = {
    id: 1,
    img: 'img',
    roomType: 'type',
    location: '101',
    reservations:[]
}
const user = {
    id: room.id,
    img: room.img,
    location: room.location,
    roomType: room.roomType
}
const admin = {
    id: room.id,
    roomType: 'type',
    location: '101',
    reservations:[]
}

const userList = [];
for(let i = 0; i < 32; i++) {userList.push(JSON.parse(JSON.stringify(user)));user.id++;user.location++;}

const adminList = [];
for(let i = 0; i < 16; i++) {adminList.push(JSON.parse(JSON.stringify(admin)));admin.id++;user.location++;}
export default{
    userList, adminList
}