package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.config.IPUtil;
import com.hzu.blog.domain.VisitLog;
import com.hzu.blog.mapper.VisitLogMapper;
import com.hzu.blog.service.VisitLogService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class VisitLogServiceImpl extends AbstractBaseServiceImpl<VisitLog, VisitLogMapper> implements VisitLogService{

    @Override
    public void saveLog(HttpServletRequest request, Long userId) {
        VisitLog  visitLog = new VisitLog();
        String requestURI = request.getRequestURI();
        visitLog.setUserId(userId);
        visitLog.setCreateDay(new Date());
        visitLog.setIp(IPUtil.getIpAddress(request));
        visitLog.setVisitStatus(true);
        visitLog.setRequestUrl(requestURI);
        dao.insert(visitLog);
    }
}
