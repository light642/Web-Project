import Mock from 'mockjs'
import room from './room'
import reservation from './reservation'
import message from './message'
Mock
    .mock('/room/user', room.userList)
    .mock('/room/admin/', room.adminList)
    .mock('/reservation/user', reservation.userList)
    .mock('/reservation/admin', reservation.adminList)
    .mock('/message/user',message.data)