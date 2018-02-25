


package persistence;

import domain.GroupOperation;


public class GroupOperationRepository extends GenericRepository<GroupOperation>
{
    public GroupOperationRepository()
    {
        super(GroupOperation.class);
    }

    public GroupOperationRepository(Class<GroupOperation> type)
    {
        super(type);
    }

}
