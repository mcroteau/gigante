package shape;

import net.plsar.*;
import net.plsar.drivers.Drivers;
import net.plsar.environments.Environments;
import net.plsar.schemes.RenderingScheme;
import net.plsar.security.renderer.AuthenticatedRenderer;
import net.plsar.security.renderer.GuestRenderer;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gigante {

	public static void main(String[] args){
		PLSAR plsar = new PLSAR(4001);
		plsar.setNumberOfPartitions(4);
		plsar.setNumberOfRequestExecutors(10);

		plsar.setSecurityAccess(AuthAccess.class);

		PersistenceConfig persistenceConfig = new PersistenceConfig();
		persistenceConfig.setDriver(Drivers.H2);
		persistenceConfig.setUrl("jdbc:h2:~/devDb");
		persistenceConfig.setUser("sa");
		persistenceConfig.setPassword("");

		SchemaConfig schemaConfig = new SchemaConfig();
		schemaConfig.setSchema("schema.sql");
		schemaConfig.setEnvironment(Environments.DEVELOPMENT);
		persistenceConfig.setSchemaConfig(schemaConfig);
		plsar.setPersistenceConfig(persistenceConfig);

		PropertiesConfig propertiesConfig = new PropertiesConfig();
		propertiesConfig.setPropertiesFile("grazie.properties");
		plsar.setPropertiesConfig(propertiesConfig);

		plsar.addViewRenderer(AuthenticatedRenderer.class);
		plsar.addViewRenderer(GuestRenderer.class);

		ViewConfig viewConfig = new ViewConfig();
		viewConfig.setViewsPath("webapp");
		viewConfig.setResourcesPath("assets");
		viewConfig.setViewExtension(".jsp");
		viewConfig.setRenderingScheme(RenderingScheme.RELOAD_EACH_REQUEST);
		plsar.setViewConfig(viewConfig);

		plsar.start();
	}

}