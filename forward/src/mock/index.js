import Mock from 'mockjs'

const data = null;

Mock
    .mock('/reservation/user1','get', data)//user1的预约列表
    .mock('/reservation/room/1','get', data)//房间1的预约列表
    .mock('/reservation','post',true)//提交预约
    .mock('/reservation/1','delete',true)//取消预约id为1的预约

    .Mock('/block','get',data)//所有被停用的房间
    .mock('/block/1','post', true)//停用房间1
    .mock('/block/1','delete', true)//取消id为1的停用

    .mock('/room','get', data)//房间列表
    .mock('/room/1','get', data)//房间1详情
    .mock('/room','post',true)//新建房间
    .mock('/room/1','delete',true)//删除房间1

    .mock('/session',"post",true)//登录
    .mock('/session',"delete",true)//退出登录
    .mock('/user',"post",true)//注册

    .mock('/message/user1','get', data)//user1的消息列表