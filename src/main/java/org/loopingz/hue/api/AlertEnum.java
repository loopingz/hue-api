/**
 * 
 */
package org.loopingz.hue.api;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public enum AlertEnum {
	/**
	 * Nothing happens
	 */
	NONE("none"),
	/**
	 * The light flash once
	 */
	SELECT("select"),
	/**
	 * The light breathe every 30s
	 */
	LSELECT("lselect");
	
	private AlertEnum(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}
}
