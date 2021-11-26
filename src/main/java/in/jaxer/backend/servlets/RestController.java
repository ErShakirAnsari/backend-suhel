
package in.jaxer.backend.servlets;

import in.jaxer.api.dtos.ApiResponseDto;
import in.jaxer.api.dtos.RequestResponseDto;
import in.jaxer.api.listners.Authentication;
import in.jaxer.api.request.AbstractRestController;
import in.jaxer.backend.db.DbConnection;
import in.jaxer.core.net.Servlets;
import in.jaxer.core.utilities.Validator;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Shakir Ansari
 */
@Log4j2
@WebServlet(urlPatterns = "/endpoint")
public class RestController extends AbstractRestController
{

	private static final long serialVersionUID = 1L;

	@Override
	protected String getBasePackage()
	{
		return "in.jaxer.backend.*";
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ApiResponseDto apiResponseCO = doProcess(request, response);
		Servlets.printJsonResponse(response, apiResponseCO, true);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try (Connection connection = DbConnection.getConnection();)
		{
			log.info("connection:{}", connection);
			Validator.requireNotNull(connection, "Unable to connect db");

			connection.setAutoCommit(false);

			ApiResponseDto apiResponseCO = doProcess(request, response, connection, new AuthenticationImpl());
			Servlets.printJsonResponse(response, apiResponseCO, true);

			connection.commit();
		} catch (Exception ex)
		{
			ApiResponseDto apiResponseCO = doProcessException(response, ex);
			Servlets.printJsonResponse(response, apiResponseCO, true);
		}
	}

	private class AuthenticationImpl implements Authentication
	{

		@Override
		public void doAuthentication(Connection connection, RequestResponseDto requestResponseDto) throws Exception
		{
//			String accessToken = requestResponseDto.getAccessToken();
//			Validator.requireNotEmpty(accessToken, new ApiException(StatusList.ACCESS_TOKEN_REQUIRED));
//
//			UserLogin userLogin = DbOperations.getUserLoginByAccessToken(connection, accessToken, requestResponseDto.getRequestSource());
//			if (userLogin == null)
//			{
//				throw new ApiException(StatusList.ACCESS_TOKEN_INVALID);
//			}
//
//			if (userLogin.getExpiryTime().getTime() < System.currentTimeMillis())
//			{
//				throw new ApiException(StatusList.ACCESS_TOKEN_EXPIRED);
//			}
//
//			User user = DbOperations.findById(connection, User.class, userLogin.getCreatorBy());
//			if (user.getActiveStatus() != ActiveStatus.ACTIVE)
//			{
//				throw new ApiException(StatusList.USER_ACCOUNT_INACTIVE);
//			}
//
//			requestResponseDto.setTemporaryObject("user", user);
		}
	}

}
