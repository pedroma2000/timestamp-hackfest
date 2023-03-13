package io.hackfest;

import io.hackfest.dbmodel.DbPosUpdate;

import javax.inject.Inject;

import java.util.UUID;

import javax.transaction.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Vector;

@Path("/pos")
public class Rest {
	public Rest() {
	}

	@Inject
	private DatabaseTable tbl;

	@GET
	@Path("/create_test")
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public boolean createTest() {
		var pos = new DbPosUpdate();
		pos.UUID = UUID.randomUUID().toString();

		tbl.persist(pos);

		return true;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAll() {
		var ret = new Vector<String>();

		for (DbPosUpdate pos: tbl.findAll().list()) {
			ret.add(pos.UUID);
		}

		return ret;
	}
}
