
package in.jaxer.backend.db;

import in.jaxer.sdbms.DbConnectionHelper;
import in.jaxer.sdbms.exceptions.SDBMSException;
import java.sql.Connection;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Shakir Ansari
 */
@Log4j2
public class DbConnection
{

	private static final String HOST = "localhost:3306";

	private static final String DB_NAME = "jaxer_coaching";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "";

	public static Connection getConnection()
	{
		try
		{
			log.debug("Before connecting to database HOST:{}, DB_NAME:{}, USERNAME:{},PASSWORD:{}", HOST, DB_NAME, USERNAME, PASSWORD);
			return DbConnectionHelper.getMySQLConnection(HOST, DB_NAME, USERNAME, PASSWORD);
		} catch (Exception ex)
		{
			log.error("Exception", ex);
			throw new SDBMSException(ex);
		}
	}
}
