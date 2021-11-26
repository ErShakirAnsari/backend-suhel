
package in.jaxer.backend.tasks;

import in.jaxer.api.annotations.ApiTask;
import in.jaxer.api.request.AbstractApiTask;
import java.sql.Connection;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Shakir Ansari
 */
@Log4j2
@ApiTask(isPublicApi = true)
public class TestAPI extends AbstractApiTask
{

	@Override
	public void doApiTask(Connection connection) throws Exception
	{
		log.info("Hello world");
		setParameter("response", "from server");
	}

}
