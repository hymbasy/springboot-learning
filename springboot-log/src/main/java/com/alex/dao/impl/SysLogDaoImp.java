package com.alex.dao.impl;

import com.alex.dao.SysLogDao;
import com.alex.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SysLogDaoImp implements SysLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveSysLog(SysLog syslog) {
        StringBuilder sql = new StringBuilder("insert into sys_log ");
        sql.append("(username,operation,time,method,params,ip,create_time) ");
        sql.append("values(:username,:operation,:time,:method,");
        sql.append(":params,:ip,:createTime)");
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        npjt.update(sql.toString(), new BeanPropertySqlParameterSource(syslog));
    }

    @Override
    public List<SysLog> listSysLog() {
        StringBuilder sql = new StringBuilder("select * from sys_log order by id asc");
        return jdbcTemplate.query(sql.toString(), new RowMapper<SysLog>() {
            @Override
            public SysLog mapRow(ResultSet resultSet, int i) throws SQLException {
                SysLog log = new SysLog();
                log.setCreateTime(resultSet.getDate("create_time"));
                log.setId(resultSet.getInt("id"));
                log.setIp(resultSet.getString("ip"));
                log.setMethod(resultSet.getString("method"));
                log.setOperation(resultSet.getString("operation"));
                log.setParams(resultSet.getString("params"));
                log.setTime(resultSet.getInt("time"));
                log.setUsername(resultSet.getString("username"));
                return log;
            }
        });
    }
}
