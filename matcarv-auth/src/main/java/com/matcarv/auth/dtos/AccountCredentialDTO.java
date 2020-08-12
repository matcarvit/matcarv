/**
 * 
 */
package com.matcarv.auth.dtos;

import java.io.Serializable;

import lombok.Data;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
public class AccountCredentialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6185816575814795138L;
	
	/**
	 * 
	 */
	private String username;
	
	/**
	 * 
	 */
	private String password;

}
