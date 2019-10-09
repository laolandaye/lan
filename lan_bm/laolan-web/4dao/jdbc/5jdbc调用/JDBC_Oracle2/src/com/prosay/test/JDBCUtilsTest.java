package com.prosay.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prosay.utils.JDBCUtils;

public class JDBCUtilsTest {

	@Test
	public void testGetConnection() {
		Connection con = JDBCUtils.getConnection();
		System.out.println(con);
	}

}
