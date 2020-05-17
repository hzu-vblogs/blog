package com.hzu.blog.mapper;

import com.hzu.blog.domain.LeaveMessage;
import tk.mybatis.mapper.MyMapper;import java.util.List;

public interface LeaveMessageMapper extends MyMapper<LeaveMessage> {
    List<LeaveMessage> selectByUserId(Long userId);

    List<LeaveMessage> selectByParentId(Long parentId);

    void deleteMulti(List<Long> ids);
}