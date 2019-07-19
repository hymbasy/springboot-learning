package com.alex.dao;

import com.alex.domain.SysLog;

import java.util.List;

public interface SysLogDao {

    void saveSysLog(SysLog syslog);

    List<SysLog> listSysLog();
}
