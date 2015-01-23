package com.UndefinedParameter.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.UndefinedParameter.app.core.Group;
import com.UndefinedParameter.app.core.Organization;

@RegisterMapper(OrganizationMapper.class)
public interface OrganizationDAO {
	
	@SqlQuery("SELECT * FROM Organization")
	public List<Organization> findOrganizations();
	
	@SqlQuery("SELECT * FROM Organization WHERE OrgID = :orgId")
	public Organization findOrganization(@Bind("orgId") int id);
	
	@SqlQuery("SELECT * FROM SubGroup WHERE OrgID = :orgId")
	public List<Group> findGroupsByOrgId(@Bind("orgId") int id);
	
	@SqlQuery("SELECT * FROM SubGroup WHERE GroupId = :groupId")
	public Group findGroupById(@Bind("groupId") int id);
	
	@SqlUpdate("INSERT INTO SubGroup (Name, Description, OrgID) VALUES (:name, :desc, :orgId)")
	public void insertGroup(@Bind("name") String name, 
							@Bind("desc") String description, 
							@Bind("orgId") int orgId);
	
	@SqlUpdate("INSERT INTO Organization (Name, Description, City, State, Country) VALUES (:name, :desc, :city, :state, :country)")
	public void insertOrganization(@Bind("name") String name,
								   @Bind("desc") String description,
								   @Bind("city") String city,
								   @Bind("state") String state,
								   @Bind("country") String country);
}
