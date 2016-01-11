/**
 * 
 */
package org.sword.wechat4j.message.template;

import java.util.List;


/**
 * @author ChengNing
 * @date   2014年12月24日
 */
public class TemplateMsgBody {
	private String touser;
	private String templateId;
	private String url;
	private String topcolor;
//	private TemplateMsgData first;
//	private TemplateMsgData remark;
//	private List<TemplateMsgData> keynote;
	private List<TemplateMsgData> data;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
//	public TemplateMsgData getFirst() {
//		return first;
//	}
//	public void setFirst(TemplateMsgData first) {
//		this.first = first;
//	}
//	public TemplateMsgData getRemark() {
//		return remark;
//	}
//	public void setRemark(TemplateMsgData remark) {
//		this.remark = remark;
//	}
//	public List<TemplateMsgData> getKeynote() {
//		return keynote;
//	}
//	public void setKeynote(List<TemplateMsgData> keynote) {
//		this.keynote = keynote;
//	}
	public List<TemplateMsgData> getData() {
		return data;
	}
	public void setData(List<TemplateMsgData> data) {
		this.data = data;
	}
	
	

	
	
}
