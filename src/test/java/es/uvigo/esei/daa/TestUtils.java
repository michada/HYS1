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
				.append("DELETE FROM `location`;")
				.append("DELETE FROM `category`;").toString();

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
						+ "VALUES (1,'2015-01-06 16:35:07','Evento cinematográfico sobre las mejores películas de terror hechas en la historia del cine.','PROGRAMMED','Retrospectiva sobre el cine de terror','PRIVATE',2,1,1);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (2,'2015-01-06 16:35:07','Semana cultural sobre el cine fantástico y de terror de San Sebastián. Se contará con invitados de la talla de Jack Ferguson o Charlie Sheen','CANCELLED','XXVI Seman de Cine Fantástic y de Terror','PUBLIC',2,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (3,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de los Juegos del Hambre. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Juegos del Hambre','PRIVATE',2,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (4,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Magic Meeting, el gran evento temático de Harry Potter!!! Pedido y esperado por muchos fans','PROGRAMMED','Magic Meeting (Harry Potter)','PUBLIC',1,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (5,'2015-01-06 16:35:07','El Fantasma de la Ópera es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El fantasma de la Ópera','PUBLIC',5,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (6,'2015-01-06 16:35:07','El próximo miércoles quedamos en el bar Plaza para ver el partido de final de Champions Manchester - Chelsea','PROGRAMMED','Partido Manchester - Chelsea','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (7,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Juego de Tronos en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (8,'2015-01-06 16:35:07','Semana cultural sobre el cine de ciencia ficción de San Sebastián. Se contará con invitados de la talla de Jack Ferguson o Charlie Sheen','PROGRAMMED','XXVI Seman de Cine de Ciencia Ficción','PRIVATE',2,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (9,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de la nueva película de Harry Potter. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Harry Potter','PRIVATE',2,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (10,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Magic Meeting, el gran evento temático de Harry Potter!!! Pedido y esperado por muchos fans','PROGRAMMED','Magic Meeting (Harry Potter)','PUBLIC',1,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (11,'2015-01-06 16:35:07','El Anillo de Oro es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El Anillo de Oro','PUBLIC',5,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (12,'2015-01-06 16:35:07','El próximo miércoles quedamos en el bar Fio de liño para ver el partido de final de Mundial Holanda - Francia','CANCELLED','Partido Holanda - Francia','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (13,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Castle en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de Castle','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (14,'2015-01-06 16:35:07','Semana cultural sobre el cine de comedia de San Sebastián. Se contará con invitados de la talla de Antonio Banderas o Nicolas Cage','COMPLETED','XXVI Seman de Cine Fantástic y de Terror','PRIVATE',2,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (15,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de Solo en Casa. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Solo en Casa','PRIVATE',2,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (16,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Chachi Meeting, el gran evento temático de Hannah Montana !!! Pedido y esperado por muchos fans','CANCELLED','Chachi Meeting (Hannah Montana)','PRIVATE',1,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (17,'2015-01-06 16:35:07','El Pequeño Enrique es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El Pequeño Enrique','PUBLIC',5,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (18,'2015-01-06 16:35:07','El próximo miércoles quedamos en el bar Juanito para ver el partido de final del Campeonato Manchester - Chelsea','PROGRAMMED','Partido Manchester - Chelsea','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (19,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Ana y los 7 en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de Ana y los 7','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (20,'2015-01-06 16:35:07','Semana cultural sobre el cine de acción de Ourense. Se contará con invitados de la talla de Brad Pitt','COMPLETED','XXVI Seman de Cine de Acción','PUBLIC',2,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (21,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de A todo Gas. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Two Fast Two Furious','PUBLIC',2,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (22,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Frodo Meeting, el gran evento temático del Señor de los anillos!!! Pedido y esperado por muchos fans','PROGRAMMED','Frodo Meeting (El señor de los anillos)','PUBLIC',1,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (23,'2015-01-06 16:35:07','El Cascanueces es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El Cascanueces','PUBLIC',5,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (24,'2015-01-06 16:35:07','El próximo domingo quedamos en el bar Plaza para ver el partido de final de Copa Depor - Celta','PROGRAMMED','Partido Depor - Celta','PRIVATE',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (25,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Anatomía de Grey en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de Anatomía de Grey','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (26,'2015-01-06 16:35:07','Semana cultural sobre el cine de suspense de Madrid. Se contará con invitados de la talla de Richard Gere','PROGRAMMED','XXVI Seman de Cine de Suspense','PRIVATE',2,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (27,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de Saw. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Saw','PRIVATE',2,3,3);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (28,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Spider Meeting, el gran evento temático de Spiderman!!! Pedido y esperado por muchos fans','PROGRAMMED','Spider Meeting (Spiderman)','PRIVATE',1,1,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (29,'2015-01-06 16:35:07','Blancanieves es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','Blancanieves','PUBLIC',5,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (30,'2015-01-06 16:35:07','El próximo sábado quedamos en el bar Auriense para ver el partido de final de Champions Manchester - Chelsea','PROGRAMMED','Partido Manchester - Chelsea','PUBLIC',4,3,2);")
				.append("INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) "
						+ "VALUES (31,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie House en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de House','PRIVATE',4,3,2);")
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