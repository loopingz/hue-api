/**
 * 
 */
package org.loopingz.hue.api;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class SoftwareUpdate {
	/**
	 * Update state
	 */
	private Integer updatestate;
	/**
	 * URL ?
	 */
	private String url;
	/**
	 * Text ?
	 */
	private String text;
	/**
	 * Notify ?
	 */
	private Boolean notify;
	/**
	 * @return the updatestate
	 */
	public Integer getUpdatestate() {
		return updatestate;
	}
	/**
	 * @param updatestate the updatestate to set
	 */
	public void setUpdatestate(Integer updatestate) {
		this.updatestate = updatestate;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the notify
	 */
	public Boolean getNotify() {
		return notify;
	}
	/**
	 * @param notify the notify to set
	 */
	public void setNotify(Boolean notify) {
		this.notify = notify;
	}
}
