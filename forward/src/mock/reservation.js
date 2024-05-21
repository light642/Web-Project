const reservation = {
    id: 1,
    roomId: 1,
    userId: 1,
    start: '1-1-2000',
    end: '1-1-2000'
}
const user = {
    id: reservation.id,
    roomId: reservation.roomId,
    start: reservation.start,
    end: reservation.end
}
const admin = {
    id: reservation.id,
    roomId: reservation.roomId,
    userId: reservation.userId,
    start: reservation.start,
    end: reservation.end
}


const userList = [];
for(let i = 0; i < 10; i++) userList.push(JSON.parse(JSON.stringify(user)));

const adminList = [];
for(let i = 0; i < 10; i++) adminList.push(JSON.parse(JSON.stringify(admin)));

export default{
    adminList, userList
}