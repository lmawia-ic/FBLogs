package fb.logs.model;

import java.util.Map;

public class FBLogRequest {

	private Timestamp timestamp;
	private String level;
	private String source;
	private String workflow;
	private int tenantId;
	private int campaignId;
	private Member member;
	private Map<String, Object> additionalFields;
	private String message;
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Map<String, Object> getAdditionalFields() {
		return additionalFields;
	}
	public void setAdditionalFields(Map<String, Object> additionalFields) {
		this.additionalFields = additionalFields;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "FBLogRequest [timestamp=" + timestamp + ", level=" + level + ", source=" + source + ", workflow="
				+ workflow + ", tenantId=" + tenantId + ", campaignId=" + campaignId + ", member=" + member
				+ ", additionalFields=" + additionalFields + ", message=" + message + "]";
	}
	
}
