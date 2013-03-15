/**
 * 
 */
package org.loopingz.hue.api;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class LightState {
	/**
	 * Power status
	 */
	private Boolean on;
	
	/**
	 * Brightness
	 */
	private Integer bri;
	
	/**
	 * Hue
	 */
	private Integer hue;
	
	/**
	 * Saturation
	 */
	private Integer sat;
	
	/**
	 * CT?
	 */
	private Integer ct;
	
	/**
	 * XY ?
	 */
	private Float[] xy;
	
	/**
	 * Alert ?
	 */
	private String alert;
	
	/**
	 * Effect ?
	 */
	private String effect;
	
	/**
	 * Color mode ?
	 */
	private String colormode;
	
	/**
	 * Reachable
	 */
	private Boolean reachable;

	/**
	 * @return the on
	 */
	public Boolean getOn() {
		return on;
	}

	/**
	 * @param on the on to set
	 */
	public void setOn(Boolean on) {
		this.on = on;
	}

	/**
	 * @return the bri
	 */
	public Integer getBri() {
		return bri;
	}

	/**
	 * @param bri the bri to set
	 */
	public void setBri(Integer bri) {
		this.bri = bri;
	}

	/**
	 * @return the hue
	 */
	public Integer getHue() {
		return hue;
	}

	/**
	 * @param hue the hue to set
	 */
	public void setHue(Integer hue) {
		this.hue = hue;
	}

	/**
	 * @return the sat
	 */
	public Integer getSat() {
		return sat;
	}

	/**
	 * @param sat the sat to set
	 */
	public void setSat(Integer sat) {
		this.sat = sat;
	}

	/**
	 * @return the ct
	 */
	public Integer getCt() {
		return ct;
	}

	/**
	 * @param ct the ct to set
	 */
	public void setCt(Integer ct) {
		this.ct = ct;
	}

	/**
	 * @return the xy
	 */
	public Float[] getXy() {
		return xy;
	}

	/**
	 * @param xy the xy to set
	 */
	public void setXy(Float[] xy) {
		this.xy = xy;
	}

	/**
	 * @return the alert
	 */
	public String getAlert() {
		return alert;
	}

	/**
	 * @param alert the alert to set
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/**
	 * @return the effect
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * @param effect the effect to set
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}

	/**
	 * @return the colormode
	 */
	public String getColormode() {
		return colormode;
	}

	/**
	 * @param colormode the colormode to set
	 */
	public void setColormode(String colormode) {
		this.colormode = colormode;
	}

	/**
	 * @return the reachable
	 */
	public Boolean getReachable() {
		return reachable;
	}

	/**
	 * @param reachable the reachable to set
	 */
	public void setReachable(Boolean reachable) {
		this.reachable = reachable;
	}
}
