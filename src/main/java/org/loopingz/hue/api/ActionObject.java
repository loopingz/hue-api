/**
 * 
 */
package org.loopingz.hue.api;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public abstract class ActionObject {


	/**
	 * Object id
	 */
	private String id;
	/**
	 * Name
	 */
	private String name;
	
	/**
	 * State
	 */
	private LightState state;
	/**
	 * Bridge the light is connected to
	 */
	private Bridge bridge;
	
	/**
	 * @return the bridge
	 */
	public Bridge getBridge() {
		return bridge;
	}

	/**
	 * @param bridge the bridge to set
	 */
	public void setBridge(Bridge bridge) {
		this.bridge = bridge;
	}
	

	/**
	 * @return the state
	 */
	public LightState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(LightState state) {
		this.state = state;
	}

	
	/**
	 * Change color according to Color Temperature 
	 * @param ct Color temperature
	 * @param transition in 0,1s ( by default 400 ms )
	 * @throws IOException
	 */
	public void changeColor(Integer ct, Integer transition) throws IOException {
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("ct", ct);
		if (transition != null) {
			values.put("transitiontime",transition);
		}
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setCt(ct);
		getState().setColormode("ct");
	}
	
	/**
	 * Change color according to Hue and Saturation 
	 * @param hue
	 * @param saturation
	 * @param transition in 0,1s ( by default 400 ms )
	 * @throws IOException
	 */
	public void changeColor(Integer hue, Integer saturation, Integer transition) throws IOException {
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("hue", hue);
		values.put("sat", saturation);
		if (transition != null) {
			values.put("transitiontime",transition);
		}
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setHue(hue);
		getState().setSat(saturation);
		getState().setColormode("hs");
	}
	
	/**
	 * Change color according to CIE color space 
	 * @param x
	 * @param y
	 * @param transition in 0,1s ( by default 400 ms )
	 * @throws IOException
	 */
	public void changeColor(Float x, Float y, Integer transition) throws IOException {
		if (x < 0 || x > 1 || y < 0 || y > 1) {
			throw new IllegalArgumentException();
		}
		Float[] args = new Float[2];
		args[0]=x;
		args[1]=y;
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("xy", args);
		if (transition != null) {
			values.put("transitiontime",transition);
		}
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setXy(args);
		getState().setColormode("xy");
	}
	
	/**
	 * @param alarm to set
	 * @throws IOException 
	 */
	public void setAlert(AlertEnum alert) throws IOException {
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("alert", alert.getValue());
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setAlert(alert.getValue());
	}
	

	/**
	 * Switch on the light
	 * @throws IOException
	 */
	public void on() throws IOException {
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("on", true);
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setOn(true);
	}
	
	/**
	 * Switch off the light
	 * @throws IOException
	 */
	public void off() throws IOException {
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("on", false);
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setOn(false);
	}
	
	/**
	 * @param brightness can't be greater than 255
	 * @throws IOException
	 */
	public void setBrightness(int brightness, Integer transition) throws IOException {
		if (brightness > 255) {
			throw new IllegalArgumentException();
		}
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("bri", brightness);
		if (transition != null) {
			values.put("transitiontime",transition);
		}
		bridge.changeState(getChangeStateRelativeUrl(),values);
		getState().setBri(brightness);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	protected abstract String getChangeStateRelativeUrl();
}
