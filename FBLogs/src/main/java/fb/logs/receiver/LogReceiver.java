package fb.logs.receiver;

import fb.logs.model.FBLogRequest;

/**
 * Receive logs from other applications. 
 */
public interface LogReceiver {

	void recieveLogs(FBLogRequest logRequest);
	
}
