package nyla.solutions.net.postit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import nyla.solutions.net.postit.data.Recipient;

@Repository
public interface RecipientDAORepository extends CrudRepository<Recipient, String>
{

}
