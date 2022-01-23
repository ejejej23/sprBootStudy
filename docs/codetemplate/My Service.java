#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import $PACKAGE_NAME.replace(".service", ".domain.${DomainClass}");
import $PACKAGE_NAME.replace(".service", ".domain.repository.${DomainClass}Repository");
import $PACKAGE_NAME.replace(".service", ".dto.${DomainClass}Dto");

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

#set( $capitalizeDomain = $DomainClass.substring(0,1).toLowerCase() + $DomainClass.substring(1))
    #set( $lowerCaseDomain = $DomainClass.toLowerCase())
/**
 * @author ${USER}
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ${DomainClass}Service {
    private final ${DomainClass}Repository ${capitalizeDomain}Repository;

    public ${DomainClass}Dto.Response create${DomainClass}(final ${DomainClass}Dto.Create dto) {

        return null;
    }

    @Transactional(readOnly = true)
    public ${DomainClass}Dto.Response find${DomainClass}(String ${keyColumn}) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<${DomainClass}Dto.ResponseList> find${DomainClass}s(final ${DomainClass}Dto.Search searchDto, final Pageable pageable) {
        return ${capitalizeDomain}Repository.find${DomainClass}s(searchDto, pageable);
    }

    public ${DomainClass}Dto.Response update${DomainClass}(final String ${keyColumn}, final ${DomainClass}Dto.Update dto) {
        return null;
    }

    public void delete${DomainClass}(final String ${keyColumn}) {

    }
}
