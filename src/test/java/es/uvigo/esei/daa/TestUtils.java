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
				.append("DELETE FROM `event`;")
				.append("DELETE FROM `user`;")
				.append("DELETE FROM `location`;")
				.append("DELETE FROM `category`;")
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
				.append("ALTER TABLE location AUTO_INCREMENT = 1;")
				.append("ALTER TABLE category AUTO_INCREMENT = 1;")
				.append("ALTER TABLE user AUTO_INCREMENT = 1;")
				.append("ALTER TABLE event AUTO_INCREMENT = 1;")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (1, 0.0, 0.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (2, 10.0, 10.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (3, 20.0, 20.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (4, 30.0, 30.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (5, 40.0, 40.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (6, 50.0, 50.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (7, 60.0, 60.0);")
				.append("INSERT INTO location (id,latitude,longitude) VALUES (8, 70.0, 70.0);")
				.append("INSERT INTO category (`id`, `name`) VALUES (1, 'Book');")
				.append("INSERT INTO category (`id`, `name`) VALUES (2, 'Film');")
				.append("INSERT INTO category (`id`, `name`) VALUES (3, 'TV series');")
				.append("INSERT INTO category (`id`, `name`) VALUES (4, 'Sport Event');")
				.append("INSERT INTO category (`id`, `name`) VALUES (5, 'Theater');")
				.append("INSERT INTO `user` VALUES (1,'1f43ca92e76ab25c659f0b29432e2f13776e044165ace94d718a3cfb20fb1c20','Ray');")
				.append("INSERT INTO `user` VALUES (2,'6b36f3357f1b05d7e4754c4b868d73fa4a8269dc7eb2bf317bfb9b56b2df2c3c','Derek');")
				.append("INSERT INTO `user` VALUES (3,'7c6e1bf16286b2bcb2790eee9d42a406f0de86615436d85baf86efa3b7d9eb20','Wylie');")
				.append("INSERT INTO `user` VALUES (4,'e8fbf44c43373ad0691e03cae826ea4e04e2117076228fe76d7318fd94a4ae66','Moses');")
				.append("INSERT INTO `user` VALUES (5,'0b802ee61d8451c6c8a726d6f1c7e58c054d63994fd9a8c53a85ed6e3cb5ba2a','Buckminster');")
				.append("INSERT INTO `user` VALUES (6,'6a132d36b16f91cea834d30576207719c2fc70cee15403add8bc550c04bd999e','Macon');")
				.append("INSERT INTO `user` VALUES (7,'3aed202dc7d798311d6ec07f992ecf3dd472972fd9fee174dcb3582c7483bbe6','Edan');")
				.append("INSERT INTO `user` VALUES (8,'61a1d3bdf6653544be9579821e40a5a376743e1f846421bad02b3ec49f8fd22a','Lyle');")
				.append("INSERT INTO `user` VALUES (9,'cb115a7ab849366a9c7c20accdc447ade3b9194d421d82ff687cf4dcc690cabc','Kane');")
				.append("INSERT INTO `user` VALUES (10,'52d7d8604bf71e968bf07d468c7d394bff7cb0bb142fd4da7a85dd6f8056a940','Omar');")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (1,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','PROGRAMMED','Courtney','PRIVATE',1,1,1);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (2,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','CANCELLED','Courtney','PUBLIC',2,2,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (3,'2015-01-06 16:35:07','Hall of Fame','PROGRAMMED','Gala Grammy','PRIVATE',3,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (4,'2015-01-06 16:35:07','Wonderful description','PROGRAMMED','Gala Goya','PUBLIC',5,4,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (5,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','CANCELLED','Courtney','PUBLIC',4,5,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (6,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','COMPLETED','Courtney','PRIVATE',1,6,1);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (7,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','COMPLETED','Courtney','PUBLIC',1,7,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (8,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','CANCELLED','Courtney','PRIVATE',2,8,1);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (9,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','PROGRAMMED','Courtney','PUBLIC',2,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (10,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','PROGRAMMED','Courtney','PUBLIC',5,2,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (11,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','PROGRAMMED','Courtney','PUBLIC',1,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (12,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','PROGRAMMED','Courtney','PUBLIC',2,4,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (13,'2015-01-06 16:35:07','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur.Leonard','PROGRAMMED','Courtney','PUBLIC',1,5,2);")
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