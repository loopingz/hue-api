package org.loopingz.hue.api;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.loopingz.hue.api.Bridge;
import org.loopingz.hue.api.BridgeDetectionException;
import org.loopingz.hue.api.Light;

/**
 * 
 */

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class BridgeTest {
	private Bridge bridge = null;
	//@Test
	public void listBridges() throws BridgeDetectionException, IOException, InterruptedException {
		bridge = Bridge.get();
		System.out.println(bridge);
		if (!bridge.isRegister("JAVAAPITEST")) {
			System.out.println("The java api is not registered, please push the button on your bridge in the next 30s");
			if (!bridge.register("JavaApiTest","JAVAAPITEST",30)) {
				System.out.println("Can't register on bridge, giving up");
				return;
			}
			System.out.println("org.loopingz.hue.api test is now registered");
		} else {
			System.out.println("org.loopingz.hue.api test is registered");
		}
		setTempColor();
	}
	
	public void switchOff() throws IOException {
		getTestLight().off();
	}
	
	public void setTempColor() throws IOException {
		getTestLight().changeColor(260, null);
	}
	
	public void switchOn() throws IOException {
		getTestLight().on();
	}
	
	public void setHSColor() throws IOException {
		getTestLight().changeColor(100, 30, 24);
	}
	
	public void setXYColor() throws IOException {
		getTestLight().changeColor(0.5F, 0.5F, 7);
	}
	
	public void setBrightness() throws IOException {
		getTestLight().setBrightness(255, 10);
	}
	
	public void rename() throws IOException {
		getTestLight().rename("SaM");
	}
	
	private Light getTestLight() {
		Collection<Light> lights = bridge.listLights();
		for (Light light : lights) {
			if ("Salon".equals(light.getName())) {
				return light;
			}
		}
		return null;
	}
	
	public void select() throws IOException {
		getTestLight().setAlert(AlertEnum.SELECT);
	}
	
	public void lselect() throws IOException {
		getTestLight().setAlert(AlertEnum.LSELECT);
	}
}
