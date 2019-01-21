package com.osdevs.testspringboot.dao;

import com.osdevs.testspringboot.entity.LogDomain;

import java.util.List;
public interface LogDao {
    /**
     * 添加日志
     * @param logDomain
     * @return
     */
    int addLog(LogDomain logDomain);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLogById(Integer id);

    /**
     * 获取日志
     * @return
     */
    List<LogDomain> getLogs();
}
