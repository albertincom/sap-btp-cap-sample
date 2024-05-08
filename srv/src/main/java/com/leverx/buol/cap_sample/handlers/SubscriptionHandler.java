package com.leverx.buol.cap_sample.handlers;

import com.sap.cds.services.auditlog.AuditLogService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.mt.DeploymentService;
import com.sap.cds.services.mt.SubscribeEventContext;
import org.springframework.stereotype.Component;

@Component
@ServiceName(DeploymentService.DEFAULT_NAME)
public class SubscriptionHandler implements EventHandler {

	private AuditLogService auditLog;

	public SubscriptionHandler(AuditLogService auditLog) {
		this.auditLog = auditLog;
	}

	@After
	public void afterSubscribe(SubscribeEventContext context) {
		String msg = String.format("New tenant '%s' subscribed.", context.getTenant());

		// send audit log security message to provider tenant as user's tenant is null
		auditLog.logSecurityEvent("tenant subscribed", msg);
	}

}

/*
package com.leverx.buol.cap_sample.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.mt.MtSubscriptionService;
import com.sap.cds.services.mt.MtUnsubscribeEventContext;

@Component
@ServiceName(MtSubscriptionService.DEFAULT_NAME)
public class SubscriptionHandler implements EventHandler {

	@Before(event = MtSubscriptionService.EVENT_SUBSCRIBE)
	public void beforeSubscription(MtSubscribeEventContext context) {
		// Activities before tenant database container is created
	}

}
*/
