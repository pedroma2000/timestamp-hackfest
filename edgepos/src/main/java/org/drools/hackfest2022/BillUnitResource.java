package org.drools.hackfest2022;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;

import io.hackfest.PosUpdate;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/bill")
public class BillUnitResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(BillUnitResource.class);

    @javax.inject.Inject
    RuleUnit<BillUnitData> ruleUnit;

	@Channel("posupdates")
	Emitter<PosUpdate> posUpdateEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BillUnitData rules(BillUnitData unitDTO) {
		LOGGER.info("Billing request: {}", unitDTO.getItems());

        RuleUnitInstance<BillUnitData> instance = ruleUnit.createInstance(unitDTO);
        instance.fire();
        instance.dispose();

		if (unitDTO.isCheckout) {
			LOGGER.info("Checkout!");
			PosUpdate posUpdate = new PosUpdate();
			UUID uuid = UUID.randomUUID();
			posUpdate.updateText = uuid.toString();

			posUpdateEmitter.send(posUpdate);
		}

        return unitDTO; 
    }
}
