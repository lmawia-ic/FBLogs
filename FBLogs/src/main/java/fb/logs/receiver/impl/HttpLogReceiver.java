package fb.logs.receiver.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fb.logs.model.FBLogRequest;
import fb.logs.receiver.LogReceiver;
import fb.logs.service.LogProcessor;

/**
 * Receive logs from HTTP sources.
 */
@RestController
@RequestMapping("logs")
public class HttpLogReceiver implements LogReceiver {

	@Autowired
	private LogProcessor logsProcessor;
	private static final String CHANNEL = "HTTP";
	
	private static final Logger logger = LogManager.getLogger(HttpLogReceiver.class);
	
	@RequestMapping(value="insert", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void insertLogEndpoint(@RequestBody FBLogRequest logRequest) {
		logger.debug("Log received from HTTP");
		recieveLogs(logRequest);
	}

	@Override
	public void recieveLogs(FBLogRequest logRequest) {
		logsProcessor.insertLog(logRequest, CHANNEL);
	}
	
}
