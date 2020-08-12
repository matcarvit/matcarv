/**
 * 
 */
package com.matcarv.products.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.matcarv.products.business.ProductBusiness;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class ProductScheduler {
	
	private static final Log log = LogFactory.getLog(ProductScheduler.class);
	
	@Getter
	@Autowired
	private transient ProductBusiness productBusiness;

	/**
	 * 
	 */
	@Scheduled(fixedDelay = 60000)
	public void execute() {
		log.info("Iniciando as tasks de processamento de produtos contidos no cache...");
		
		getProductBusiness().processCreatedProductFromCache();
		getProductBusiness().processCancelledProductFromCache();
	}
}
