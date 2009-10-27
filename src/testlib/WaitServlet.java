package testlib;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WaitServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(WaitServlet.class.getName());
	private static final String CONTEXT_ID = "ContextID";
	private static final String THREAD_LOG_FORMAT = "UUID=%s,TGCount=%d,TId=%d,THashcode=%d,RHashCode=%d";
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		ServletContext ctx = getServletContext();
		//Print about thread.
		Thread ct = Thread.currentThread();
		logger.warning(String.format(THREAD_LOG_FORMAT,	
				ctx.getAttribute(CONTEXT_ID),
				ct.getThreadGroup().activeCount(),
				ct.getId(),
				ct.hashCode(),
				Runtime.getRuntime().hashCode()));
		try {
			Thread.sleep(1000);
			resp.getWriter().println("OK");
			logger.warning("success");
		} catch ( Exception e ) {
			logger.log(Level.WARNING,"Getting error", e);
			resp.sendError(500);
			logger.warning("failure");
		}
		
	}
}
