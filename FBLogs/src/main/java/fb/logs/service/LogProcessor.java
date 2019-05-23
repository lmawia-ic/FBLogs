package fb.logs.service;

import fb.logs.model.FBLogRequest;

public interface LogProcessor {

	void insertLog(FBLogRequest logRequest, String channel);
	
}
