/**
 * 
 */
package org.loopingz.hue.api;

import java.util.Map;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class BridgeStatus {
	
	/**
	 * Lights
	 */
	private Map<String,Light> lights = null;
	
	/**
	 * Groups
	 */
	private Map<String,Group> groups = null;
	
	/**
	 * Configuration
	 */
	private ConfigStatus config = null;
	
	/**
	 * Schedules
	 */
	private Map<String,Schedule> schedules = null;

	/**
	 * @return the lights
	 */
	public Map<String, Light> getLights() {
		return lights;
	}

	/**
	 * @param lights the lights to set
	 */
	public void setLights(Map<String, Light> lights) {
		this.lights = lights;
	}

	/**
	 * @return the groups
	 */
	public Map<String, Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Map<String, Group> groups) {
		this.groups = groups;
	}

	/**
	 * @return the config
	 */
	public ConfigStatus getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(ConfigStatus config) {
		this.config = config;
	}

	/**
	 * @return the schedules
	 */
	public Map<String, Schedule> getSchedules() {
		return schedules;
	}

	/**
	 * @param schedules the schedules to set
	 */
	public void setSchedules(Map<String, Schedule> schedules) {
		this.schedules = schedules;
	}
}
