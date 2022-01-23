#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import $PACKAGE_NAME.replace(".domain.repository", ".dto.${EntityClass}Dto");
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

#parse("File Header.java")

/**
 * @author ${USER}
 */
public interface ${EntityClass}SupportRepository {
    public Page<${EntityClass}Dto.ResponseList> find${EntityClass}s(final ${EntityClass}Dto.Search dto, final Pageable pageable);
}