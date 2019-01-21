package com.osdevs.testspringboot.service.log.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.osdevs.testspringboot.dao.LogDao;
import com.osdevs.testspringboot.entity.LogDomain;
import com.osdevs.testspringboot.result.error.GlobalErrorInfoEnum;
import com.osdevs.testspringboot.result.exception.GlobalErrorInfoException;
import com.osdevs.testspringboot.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        LogDomain logDomain = new LogDomain();
        logDomain.setAuthorId(authorId);
        logDomain.setIp(ip);
        logDomain.setData(data);
        logDomain.setAction(action);
        logDao.addLog(logDomain);
    }

    @Override
    public void deleteLogById(Integer id) throws GlobalErrorInfoException {
        if (null == id)
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NOT_FOUND);
        deleteLogById(id);
    }

    @Override
    public PageInfo<LogDomain> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogDomain> logs = logDao.getLogs();
        PageInfo<LogDomain> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
