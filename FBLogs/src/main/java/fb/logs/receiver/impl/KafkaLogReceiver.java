package fb.logs.receiver.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import fb.logs.model.FBLogRequest;
import fb.logs.receiver.LogReceiver;
import fb.logs.service.LogProcessor;

/**
 * Receive logs from Kafka.
 */
@Component
public class KafkaLogReceiver implements LogReceiver {

	@Autowired
	private LogProcessor logsProcessor;
	private static final String CHANNEL = "KAFKA";
	
	private static Logger logger = LogManager.getLogger(KafkaLogReceiver.class);
	private static ObjectMapper mapper = new ObjectMapper();
	
	@KafkaListener(topics = "fb_logs", groupId="group_fb_logs")
	public void recieveKafkaLogs(String jsonLog) {
		logger.debug("Log received from Kafka");
		FBLogRequest logRequest = null;
		try {
			logRequest = mapper.treeToValue(mapper.readTree(jsonLog), FBLogRequest.class);
		} catch (IOException e) {
			logger.error("Could not parse JSON from Kafka", e);
		}
		recieveLogs(logRequest);
	}

	@Override
	public void recieveLogs(FBLogRequest logRequest) {
		if (logRequest != null) {
			logsProcessor.insertLog(logRequest, CHANNEL);
		} else {
			logger.info("logRequest is NULL");
		}
	}

}
