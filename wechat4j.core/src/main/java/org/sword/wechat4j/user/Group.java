package org.sword.wechat4j.user;
/**
 * 分组
 * @author Zhangxs
 * @version 2015-7-5
 */
public class Group {
	/**分组id*/
	private Integer id;
	/**分组名字*/
	private String name;
	/**分组内用户数量*/
	private int count;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
