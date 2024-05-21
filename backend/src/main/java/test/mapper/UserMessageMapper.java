package test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import test.entity.UserMessage;

@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {
}
