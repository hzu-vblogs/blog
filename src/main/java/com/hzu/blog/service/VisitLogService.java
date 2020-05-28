package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.VisitLog;

import javax.servlet.http.HttpServletRequest;

public interface VisitLogService extends BaseService<VisitLog> {
    void saveLog(HttpServletRequest request,Long userId);

}
