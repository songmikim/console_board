package org.koreait.global.configs;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {
    @Test
    void test1() throws SQLException {
        DBConn dbConn = DBConn.getInstance();
        DataSource ds = dbConn.dataSource();
        Connection con = ds.getConnection();
        System.out.println(con);
    }
}
