#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import $PACKAGE_NAME.replace(".repository", ".${EntityClass}");
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

#parse("File Header.java")

/**
 * @author ${USER}
 */
public interface ${EntityClass}Repository extends JpaRepository<${EntityClass}, String>, ${EntityClass}SupportRepository,
    QuerydslPredicateExecutor<${EntityClass}> {
}