package testlib;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class MyStartupServlet  extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(MyStartupServlet.class.getName());
	private static final String CONTEXT_ID = "ContextID";
	private static final String THREAD_LOG_FORMAT = "UUID=%s,TGCount=%d,TId=%d,THashcode=%d,RHashCode=%d";

	@Override
	public void init(final ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext ctx = config.getServletContext();
		String ctxid = null;
		if ( ctx != null ) {
			ctxid = UUID.randomUUID().toString();
			ctx.setAttribute(CONTEXT_ID,ctxid);
		}
		//Print about this thread.
		Thread ct = Thread.currentThread();
		logger.warning(String.format(THREAD_LOG_FORMAT,	
				ctx.getAttribute(CONTEXT_ID),
				ct.getThreadGroup().activeCount(),
				ct.getId(),
				ct.hashCode(),
				Runtime.getRuntime().hashCode()));
	}

	@Override
	public void service(final ServletRequest req, final ServletResponse res)
			throws ServletException, IOException {
	}
}
