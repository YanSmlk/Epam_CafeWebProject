package by.yan.cafe.repository;

import by.yan.cafe.exception.RepositoryException;
import java.util.List;

public interface Repository
{
    void insert(SqlSpecification specification) throws RepositoryException;
    List query(SqlSpecification specification) throws RepositoryException;
    List stringQuery(SqlSpecification specification) throws RepositoryException;
}
