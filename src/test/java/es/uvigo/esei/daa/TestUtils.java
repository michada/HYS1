package es.uvigo.esei.daa;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public final class TestUtils {
	private final static SimpleNamingContextBuilder CONTEXT_BUILDER = new SimpleNamingContextBuilder();

	private TestUtils() {
	}

	public static void createFakeContext() throws NamingException {
		createFakeContext(createTestingDataSource());
	}

	public static void createFakeContext(DataSource datasource)
			throws IllegalStateException, NamingException {
		CONTEXT_BUILDER.bind("java:/comp/env/jdbc/hys1", datasource);
		CONTEXT_BUILDER.activate();
	}

	public static void clearContextBuilder() {
		CONTEXT_BUILDER.clear();
		CONTEXT_BUILDER.deactivate();
	}

	public static BasicDataSource createTestingDataSource() {
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/hys1test?allowMultiQueries=true");
		ds.setUsername("daa");
		ds.setPassword("daa");
		ds.setMaxActive(100);
		ds.setMaxIdle(30);
		ds.setMaxWait(10000);
		return ds;
	}

	public static BasicDataSource createEmptyDataSource() {
		return new BasicDataSource();
	}

	public static void clearTestDatabase() throws SQLException {
		final String queries = new StringBuilder()
				.append("DELETE FROM `event`;").append("DELETE FROM `user`;")
				.toString();

		final DataSource ds = createTestingDataSource();
		try (Connection connection = ds.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(queries);
			}
		}
	}

	public static void initTestDatabase() throws SQLException {
		final String queries = new StringBuilder()
				.append("ALTER TABLE event AUTO_INCREMENT = 1;")
				.append("ALTER TABLE user AUTO_INCREMENT = 1;")
				.append("INSERT INTO user (id,userName,password) VALUES (1, 'mrjato', '59189332a4abf8ddf66fde068cad09eb563b4bd974f7663d97ff6852a7910a73');")
				.append("INSERT INTO event (id,date,description,status,title,visibility,creator_id,location_id,culturalElement_id) VALUES (1,'2015-12-09','Howard','CANCELED','Lacy','PUBLIC',1,4,4);")
				.append("INSERT INTO event (id,date,description,status,title,visibility,creator_id,location_id,culturalElement_id) VALUES (2,'2015-12-09','Howard','CANCELED','Lacy','PRIVATE',1,4,4);")
				.toString();

		final DataSource ds = createTestingDataSource();
		try (Connection connection = ds.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(queries);
			}
		}
	}

	public static void assertOkStatus(final Response response) {
		assertEquals("Unexpected status code",
				Response.Status.OK.getStatusCode(), response.getStatus());
	}

	public static void assertBadRequestStatus(final Response response) {
		assertEquals("Unexpected status code",
				Response.Status.BAD_REQUEST.getStatusCode(),
				response.getStatus());
	}
}