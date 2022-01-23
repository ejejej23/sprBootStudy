#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import $PACKAGE_NAME.replace(".repository", ".${EntityClass}");
import org.springframework.data.jpa.repository.JpaRepository;

#parse("File Header.java")

/**
 * @author ${USER}
 */
public interface ${EntityClass}Repository extends JpaRepository<${EntityClass}, ${EntityKey}>  {
    }