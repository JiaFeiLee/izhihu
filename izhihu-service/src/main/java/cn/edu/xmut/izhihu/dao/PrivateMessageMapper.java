package cn.edu.xmut.izhihu.dao;

import cn.edu.xmut.izhihu.pojo.entity.PrivateMessage;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("PrivateMessageDAO")
public interface PrivateMessageMapper extends Mapper<PrivateMessage> {
}