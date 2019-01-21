package com.osdevs.testspringboot.service.log;

import com.github.pagehelper.PageInfo;
import com.osdevs.testspringboot.entity.LogDomain;
import com.osdevs.testspringboot.result.exception.GlobalErrorInfoException;

public interface LogService {
    /**
     * 添加
     * @param action
     * @param data
     * @param ip
     * @param authorId
     */
    void addLog(String action, String data, String ip, Integer authorId);

    /**
     * 删除日志
     * @param id
     * @return
     */
    void deleteLogById(Integer id) throws GlobalErrorInfoException;

    /**
     * 获取日志
     * @return
     */
    PageInfo<LogDomain> getLogs(int pageNum, int pageSize);
}
