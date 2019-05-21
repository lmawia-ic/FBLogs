package fb.logs.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import fb.logs.model.FBLogLevel;
import fb.logs.model.FBLogRequest;
import fb.logs.service.LogProcessor;

@Service
public class CommonLogProcessor implements LogProcessor {

	private static final Logger logger = LogManager.getLogger(LogProcessor.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	
	@Override
	public void insertLog(FBLogRequest logRequest) {
		if (logRequest.getSource() == null || logRequest.getSource().isEmpty()) {
			logger.error("Log received from an unidentified source! Log received is: " + logRequest.toString());
			return;
		}
		if (logRequest.getLevel() == null) {
			logger.error("Log received without level! Log received is: " + logRequest.toString());
			return;
		}
		logger.debug("Log received from " + logRequest.getSource());
		StringBuilder stringBuilder = new StringBuilder();
		if (logRequest.getTimestamp() == null) {
			logger.info("Timestamp is null! Using Server time. " + logRequest.toString());
			stringBuilder.append(getCurrentTimeString()).append(" ");
		} else {
			stringBuilder.append(logRequest.getTimestamp().toString()).append(" ");
		}
		stringBuilder.append(logRequest.getLevel().toUpperCase()).append(" ");
		stringBuilder.append("Source=").append(logRequest.getSource()).append(" ");
		if (logRequest.getWorkflow() != null) {
			stringBuilder.append("Workflow=").append(logRequest.getWorkflow()).append(" ");
		}
		if (logRequest.getTenantId() > 0) {
			stringBuilder.append("TenantId=").append(logRequest.getTenantId()).append(" ");
		}
		if (logRequest.getCampaignId() > 0) {
			stringBuilder.append("CampaignId=").append(logRequest.getCampaignId()).append(" ");
		}
		if (logRequest.getMember() != null) {
			if (logRequest.getMember().getMemberId() > 0) {
				stringBuilder.append("MemberId=").append(logRequest.getMember().getMemberId()).append(" ");
			}
			if (logRequest.getMember().getPhone() != null) {
				stringBuilder.append("Phone=").append(logRequest.getMember().getPhone()).append(" ");
			}
			if (logRequest.getMember().getEmail() != null) {
				stringBuilder.append("Email=").append(logRequest.getMember().getEmail()).append(" ");
			}
			if (logRequest.getMember().getName() != null) {
				stringBuilder.append("Name=").append(logRequest.getMember().getName()).append(" ");
			}
		}
		Map<String, Object> addFieldsMap = logRequest.getAdditionalFields();
		if (addFieldsMap != null && !addFieldsMap.isEmpty()) {
			for (String key : addFieldsMap.keySet()) {
				stringBuilder.append(key).append("=").append(addFieldsMap.get(key)).append(" ");
			}
		}
		stringBuilder.append("Message: ").append(logRequest.getMessage()).append(" ");
		if (logRequest.getLevel().equalsIgnoreCase(FBLogLevel.DEBUG.toString())) {
			logger.debug(stringBuilder.toString());
		} else if (logRequest.getLevel().equalsIgnoreCase(FBLogLevel.INFO.toString())) {
			logger.info(stringBuilder.toString());
		} else if (logRequest.getLevel().equalsIgnoreCase(FBLogLevel.WARN.toString())) {
			logger.warn(stringBuilder.toString());
		} else if (logRequest.getLevel().equalsIgnoreCase(FBLogLevel.ERROR.toString())) {
			logger.error(stringBuilder.toString());
		} else if (logRequest.getLevel().equalsIgnoreCase(FBLogLevel.FATAL.toString())) {
			logger.fatal(stringBuilder.toString());
		} else if (logRequest.getLevel().equalsIgnoreCase(FBLogLevel.TRACE.toString())) {
			logger.trace(stringBuilder.toString());
		} else {
			logger.info(stringBuilder.toString());
		}
	}
	
	public static String getCurrentTimeString() {
		Date date = new Date();
		String dateString = dateFormat.format(date);
		return dateString;
	}

}
