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
public class Light extends ActionObject {
	
	/**
	 * Type
	 */
	private String type;
	
	/**
	 * Model id
	 */
	private String modelid;
	
	/**
	 * Software version
	 */
	private String swversion;
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the modelid
	 */
	public String getModelid() {
		return modelid;
	}

	/**
	 * @param modelid the modelid to set
	 */
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	/**
	 * @return the swversion
	 */
	public String getSwversion() {
		return swversion;
	}

	/**
	 * @param swversion the swversion to set
	 */
	public void setSwversion(String swversion) {
		this.swversion = swversion;
	}

	
	/**
	 * Refresh light information
	 * @throws IOException 
	 */
	public void refresh() throws IOException {
		getBridge().refreshLight(this);
	}

	/**
	 * @param newName to give
	 * @throws IOException
	 */
	public void rename(String newName) throws IOException {
		getBridge().renameLight(this, newName);
		setName(newName);
	}
	
	/**
	 * Change state url
	 * @return
	 */
	@Override
	public String getChangeStateRelativeUrl() {
		return "/lights/"+getId()+"/state";
	}

}
