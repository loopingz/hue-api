/**
 * 
 */
package org.loopingz.hue.api;

import java.util.Map;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class ConfigStatus {
	
	private String name;
	
	private String mac;
	
	private Boolean dhcp;
	
	private String ipaddress;
	
	private String netmask;
	
	private String gateway;
	
	private String proxyaddress;

	private Integer proxyport;
	
	private Map<String,Application> whitelist;
	
	private String swversion;
	
	private SoftwareUpdate swupdate;
	
	private Boolean linkbutton;
	
	private Boolean portalservices;
}
