package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.OrganizationType;
import com.UndefinedParameter.app.core.Organization;

@RegisterMapper(OrganizationTypeMapper.class)
public interface OrganizationTypeDAO {
	
	@SqlQuery("SELECT * FROM OrganizationType")
	public List<OrganizationType> findAllOrganizationTypes();
}
