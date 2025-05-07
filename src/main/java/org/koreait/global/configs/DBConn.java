package org.koreait.global.configs;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.koreait.member.mappers.MemberMapper;

public class DBConn {

    private static DBConn instance;

    private final SqlSessionFactory sqlSessionFactory;

    public DBConn() {
        sqlSessionFactory = sqlSessionFactory();
    }

    public DataSource dataSource() {

        // 환경 변수
        String database = System.getenv("db");
        String username = System.getenv("username");
        String password = System.getenv("password");

        DataSource ds = new DataSource();
        // 연결설정
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://" + database);
        ds.setUsername(username);
        ds.setPassword(password);

        // 커넥션풀 설정
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);

        return ds;
    }

    public SqlSessionFactory sqlSessionFactory() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource());
        Configuration configuration = new Configuration(environment);

        configuration.setLogImpl(Slf4jImpl.class);

        configuration.addMapper(MemberMapper.class);

        return new SqlSessionFactoryBuilder().build(configuration);
    }

    public SqlSession getSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

    public SqlSession getSession() {
        return getSession(true);
    }

    public static DBConn getInstance() {
        if (instance == null) {
            instance = new DBConn();
        }

        return instance;
    }
}
