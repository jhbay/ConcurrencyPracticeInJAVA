package net.jcip.examples.ch3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDispenser
 * <p/>
 * Using ThreadLocal to ensure thread confinement
 *
 * @author Brian Goetz and Tim Peierls
 */

// ThreadLocal을 이용해, 스레드 한정 상태를 유지.
public class Ex3_10_ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    //ThreadLocal<Connection> 클래스는 Map<Thread, Connection>이라는 해시구조이며, 
    // 해당 스레드마다 해싱값을 가져오는 것으로 이해하면됨.
    // 스레드가 폐기되는 시점에서, GC가 일어난다.
    private ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
    	// 기존에 만들어진 객체를 매번 새로 생성하는 대신 이미 생성된 객체를 재활용하는 방법이다.       
    	
    	// initialValue메서드에서 값을 만들고, 해당 스레드에 초기값으로 넘겨준다.
    	public Connection initialValue() {
                    try {
                        return DriverManager.getConnection(DB_URL);
                    } catch (SQLException e) {
                        throw new RuntimeException("Unable to acquire Connection, e");
                    }
                };
            };

    public Connection getConnection() {
        return connectionHolder.get();
    }
}