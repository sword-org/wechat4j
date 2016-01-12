/**
 * 
 */
package org.sword.wechat4j.message.template;

/**
 * @author ChengNing
 * @date   2014年12月24日
 */
public class TemplateMsgData {
	private String name; //json中的数据名称
	private String value; //keynote value
	private String color; //data keynote color
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
