#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import com.allgoyou.core.api.request.ListRequest;
import com.allgoyou.core.api.response.ApiResponse;
import com.allgoyou.core.api.response.ApiResponsePage;
import $PACKAGE_NAME.replace(".controller", ".domain.${DomainClass}");
import $PACKAGE_NAME.replace(".controller", ".service.${DomainClass}Service");
import $PACKAGE_NAME.replace(".controller", ".dto.${DomainClass}Dto");

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

#set( $capitalizeDomain = $DomainClass.substring(0,1).toLowerCase() + $DomainClass.substring(1))
    #set( $lowerCaseDomain = $DomainClass.toLowerCase())
/**
 * @author ${USER}
 */
@RestController
@RequestMapping("/api/${lowerCaseDomain}s")
@RequiredArgsConstructor
public class ${DomainClass}Controller {

    private final ${DomainClass}Service ${capitalizeDomain}Service;

    @PostMapping
    public ApiResponse<${DomainClass}Dto.Response> create${DomainClass}(@RequestBody @Valid final ${DomainClass}Dto.Create dto) {
        return ApiResponse.createOK(${capitalizeDomain}Service.create${DomainClass}(dto));
    }

    @GetMapping("/{${keyColumn}}")
    public ApiResponse<${DomainClass}Dto.Response> find${DomainClass}(@PathVariable String ${keyColumn}) {
        return ApiResponse.createOK(${capitalizeDomain}Service.find${DomainClass}(${keyColumn}));
    }

    @GetMapping
    public ApiResponsePage find${DomainClass}s(Pageable pageable, ${DomainClass}Dto.Search dto) {
        Page<${DomainClass}Dto.ResponseList> pageDto = ${capitalizeDomain}Service.find${DomainClass}s(dto, pageable);
        return ApiResponsePage.of(pageDto);
    }

    @PutMapping("/{${keyColumn}}")
    public ApiResponse<${DomainClass}Dto.Response> update${DomainClass}(@PathVariable String ${keyColumn}, @Valid @RequestBody final ${DomainClass}Dto.Update dto) {
        return ApiResponse.createOK(${capitalizeDomain}Service.update${DomainClass}(${keyColumn}, dto));
    }

    @DeleteMapping("/{${keyColumn}}")
    public ApiResponse<String> delete${DomainClass}(@PathVariable String ${keyColumn}) {
        ${capitalizeDomain}Service.delete${DomainClass}(${keyColumn});

        return ApiResponse.DEFAULT_OK;
    }

    @DeleteMapping
    public void deleteUsers(@RequestBody ListRequest ${keyColumn}s) {

    }

}
