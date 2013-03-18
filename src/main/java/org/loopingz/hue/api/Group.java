/**
 * 
 */
package org.loopingz.hue.api;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class Group extends ActionObject {
	
	/**
	 * @param bridge to affect from
	 * @return a group of all lights connected to this bridge
	 */
	public static Group getAllLightsGroup(Bridge bridge) {
		Group result = new Group();
		result.setId("0");
		result.setBridge(bridge);
		return result;
	}
	
	/**
	 * Lights ids ( should be return by JSON deserializer )
	 */
	Set<String> lightsId = new HashSet<String>();
	
	/**
	 * Lights object
	 */
	Set<Light> lights = new HashSet<Light>();
	
	/**
	 * @param light add a light to a group
	 */
	public void addLight(Light light) {
		if (!lightsId.contains(light.getId())) {
			lightsId.add(light.getId());
		}
		lights.add(light);
	}
	
	/**
	 * @param light add a light to a group
	 */
	public void addLight(String id) {
		lightsId.add(id);
	}
	
	/**
	 * Remove a light from group
	 * No bridge action as it's not really implemented
	 * @param light
	 */
	public void removeLight(Light light) {
		lightsId.remove(light.getId());
		lights.remove(light);
	}
	
	@Override
	protected String getChangeStateRelativeUrl() {
		return "/groups/" + getId() + "/action";
	}
	
	/** As the group is not really implemented yet (only maybe for the group 0 which is all lamps) emulates group */

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#changeColor(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void changeColor(Integer ct, Integer transition) throws IOException {
		if ("0".equals(getId())) {
			super.changeColor(ct, transition);
		} else {
			for (Light light : lights) {
				light.changeColor(ct, transition);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#changeColor(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void changeColor(Integer hue, Integer saturation, Integer transition)
			throws IOException {
		if ("0".equals(getId())) {
			super.changeColor(hue, saturation, transition);
		} else {
			for (Light light : lights) {
				light.changeColor(hue, saturation, transition);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#changeColor(java.lang.Float, java.lang.Float, java.lang.Integer)
	 */
	@Override
	public void changeColor(Float x, Float y, Integer transition)
			throws IOException {
		if ("0".equals(getId())) {
			super.changeColor(x, y, transition);
		} else {
			for (Light light : lights) {
				light.changeColor(x, y, transition);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#setAlert(org.loopingz.hue.api.AlertEnum)
	 */
	@Override
	public void setAlert(AlertEnum alert) throws IOException {
		if ("0".equals(getId())) {
			super.setAlert(alert);
		} else {
			for (Light light : lights) {
				light.setAlert(alert);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#on()
	 */
	@Override
	public void on() throws IOException {
		if ("0".equals(getId())) {
			super.on();
		} else {
			for (Light light : lights) {
				light.on();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#off()
	 */
	@Override
	public void off() throws IOException {
		if ("0".equals(getId())) {
			super.off();
		} else {
			for (Light light : lights) {
				light.off();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.loopingz.hue.api.ActionObject#setBrightness(int, java.lang.Integer)
	 */
	@Override
	public void setBrightness(int brightness, Integer transition)
			throws IOException {
		if ("0".equals(getId())) {
			super.setBrightness(brightness, transition);
		} else {
			for (Light light : lights) {
				light.setBrightness(brightness, transition);
			}
		}
	}
}
