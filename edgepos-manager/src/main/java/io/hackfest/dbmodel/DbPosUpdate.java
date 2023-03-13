package io.hackfest.dbmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name="posupdates")
public class DbPosUpdate {
	@Id
	public String UUID;

	@Column
	public String updated;
}
