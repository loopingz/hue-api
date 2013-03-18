/**
 * 
 */
package org.loopingz.hue.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;

/**
 * @author Rémi Cattiau <rcattiau@loopingz.com>
 *
 */
public class Bridge {
	
	/**
	 * Get status
	 */
	private BridgeStatus status = null;
	/**
	 * Application key ( use as the username )
	 */
	private String applicationKey = null;
	/**
	 * Id ( it's the mac adress if not customized )
	 */
	private String id = null;
	
	/**
	 * Internal ip address
	 */
	private String ip = null;
	
	/**
	 * Mac address
	 */
	private String macaddress = null;
	
	/**
	 * Build the bridge with a known ip
	 * @param ip
	 */
	private Bridge(String ip) {
		setIp(ip);
	}
	
	/**
	 * Get the bridge with the one detected on the local network
	 * @throws BridgeDetectionException 
	 */	
	public static Bridge get() throws BridgeDetectionException {
		Set<Bridge> bridges = list();
		
		if (bridges.size() == 0) {
			throw new BridgeDetectionException("No bridges detected");
		}
		
		if (bridges.size() > 1) {
			throw new BridgeDetectionException("Several bridges detected");
		}
		
		return bridges.iterator().next();
	}
	
	/**
	 * Get the bridge with corresponding ip
	 * @param ip to get from
	 */
	public static Bridge get(String ip) {
			return new Bridge(ip);
	}
	
	/**
	 * @param request to execute
	 * @return the result as string
	 * @throws IOException
	 */
	private String doRequest(HttpUriRequest request) throws IOException  {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = null;
		response = httpClient.execute(request);
		StringWriter writer = new StringWriter();
		IOUtils.copy(response.getEntity().getContent(), writer);
		return writer.toString();
	}
	
	/**
	 * @param applicationKey to test
	 * @return registration of this app on bridge
	 * @throws IOException if any network connection problem
	 */
	public boolean isRegister(String applicationKey) throws IOException {
		setApplicationKey(applicationKey);
		String result = doRequest(new HttpGet(getRootUrl()));
		
		if (result.contains("unauthorized user")) {
			return false;
		}
		Gson gson = new Gson();
		status = gson.fromJson(result, BridgeStatus.class);
		for (Entry<String,Light> light : status.getLights().entrySet()) {
			light.getValue().setId(light.getKey());
			light.getValue().setBridge(this);
		}
		for (Entry<String,Group> group : status.getGroups().entrySet()) {
			group.getValue().setId(group.getKey());
			group.getValue().setBridge(this);
		}
		return true;
	}
	
	/**
	 * @return get groups ( as they were on connection )
	 */
	public Collection<Group> listGroups() {
		return status.getGroups().values();
	}
	
	
	/**
	 * @param id of light to retrieve
	 * @return light if found, null if not
	 */
	public Light getLightById(String id) {
		return status.getLights().get(id);
	}
	
	/**
	 * It's better to use the getLightById
	 * @param name of light to retrieve
	 * @return light if found, null if not
	 * @see getLightById
	 */
	public Light getLightByName(String name) {
		for (Light light : status.getLights().values()) {
			if (name.equals(light.getName())) {
				return light;
			}
		}
		return null;
	}
	
	/**
	 * @return get lights ( as they were on connection )
	 */
	public Collection<Light> listLights() {
		return status.getLights().values();
	}
	
	/**
	 * List the bridge connected to your network
	 * @return
	 * @throws BridgeDetectionException 
	 */
	public static Set<Bridge> list() throws BridgeDetectionException {
		
		// Use the meet hue "upnp"
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.meethue.com/api/nupnp");
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			httpGet.releaseConnection();
			throw new BridgeDetectionException("nupnp exception", e);
		}
		
		// Now we have to unserialize answer, we don't care of UTF-8 char for now, but we should
		HashSet<Bridge> bridges = new HashSet<Bridge>();
		try {
			Gson gson = new Gson();
			HashSet<StringMap<String>> maps = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), new HashSet<Bridge>().getClass());
			for (StringMap<String> map : maps) {
				if (map.containsKey("internalipaddress")) {
					Bridge bridge = new Bridge(map.get("internalipaddress"));
					bridge.setId(map.get("id"));
					bridge.setMacAddress(map.get("macaddress"));
					bridges.add(bridge);
				}
			}
		} catch (Exception e) {
			throw new BridgeDetectionException("json exception",e);
		} finally {
			httpGet.releaseConnection();
		}

		return bridges;
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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the mac
	 */
	public String getMacAddress() {
		return macaddress;
	}

	/**
	 * @param mac the mac to set
	 */
	public void setMacAddress(String mac) {
		this.macaddress = mac;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bridge [id=" + id + ", ip=" + ip + ", macaddress=" + macaddress
				+ "]";
	}

	/**
	 * @return the applicationKey
	 */
	public String getApplicationKey() {
		return applicationKey;
	}

	/**
	 * @param applicationKey the applicationKey to set
	 */
	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

	/**
	 * Will try to register 
	 * @param applicationName The application name
	 * @param applicationKey will be used as username
	 * @param maxTry Will try to register for maxTry with one seconds pause between each tries
	 * @return false if haven't achieve to register
	 * @throws InterruptedException 
	 */
	public boolean register(String applicationName, String applicationKey, int maxTry) throws IOException, InterruptedException {
		HttpPost post = new HttpPost("http://" + getIp() + "/api");
		Gson gson = new Gson();
		// Add your data  
		HashMap<String,String> vars = new HashMap<String,String>();
		vars.put("devicetype", applicationName);
		vars.put("username", applicationKey);
		post.setEntity(new StringEntity(gson.toJson(vars)));  
		
		String result = null;
		for (int i = 0; i < maxTry; i++) {
			result = doRequest(post);
			if (result.contains("success")) {
				return true;
			}
			Thread.sleep(1000);
		}
		
		return false;
	}

	/**
	 * Refresh light attributes and state
	 * @param light
	 * @throws IOException 
	 */
	public void refreshLight(Light light) throws IOException {
		String str = doRequest(new HttpGet(getRootUrl()+"/lights/"+light.getId()));
		Gson gson = new Gson();
		Light state = gson.fromJson(str, Light.class);
		// Refresh param
		light.setState(state.getState());
		light.setType(state.getType());
		light.setName(state.getName());
		light.setModelid(state.getModelid());
		light.setSwversion(state.getSwversion());
		// TODO Copy Pointsymbol when this feature will be enable
	}
	
	/**
	 * @return the root url to use
	 */
	private String getRootUrl() {
		return "http://"+getIp()+"/api/"+getApplicationKey();
	}
	
	/**
	 * @param light to rename
	 * @param name new name
	 * @throws IOException 
	 */
	public void renameLight(Light light, String name) throws IOException {
		Gson gson = new Gson();
		HttpPut put = new HttpPut(getRootUrl()+"/lights/"+light.getId());
		HashMap<String,String> vals = new HashMap<String,String>();
		vals.put("name", name);
		put.setEntity(new StringEntity(gson.toJson(vals)));
		String str = doRequest(put);
		if (!str.contains("success")) {
			throw new IOException(str);
		}
	}
	
	/**
	 * @param light to change state
	 * @param values to change
	 * @throws IOException
	 */
	public void changeState(String relativeUrl, HashMap<String, Object> values) throws IOException {
		Gson gson = new Gson();
		HttpPut put = new HttpPut(getRootUrl() + relativeUrl);
		put.setEntity(new StringEntity(gson.toJson(values)));
		String str = doRequest(put);
		
		// We should have as many success as command
		// TODO Improve this piece of code, cause i'm too lazy tonight
		Integer count = values.size();
		Integer offset = 0;
		while ((offset = str.indexOf("success",offset) + 1) > 0) {
			count--;
		}
		if (count > 0) {
			throw new IOException(str);
		}		
	}

	/**
	 * Launch a new research for lights
	 * @throws IOException 
	 */
	public void scanLights() throws IOException {
		doRequest(new HttpPut(getRootUrl()+"/lights"));
	}

}
