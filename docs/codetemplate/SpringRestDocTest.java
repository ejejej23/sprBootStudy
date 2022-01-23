

import static com.allgoyou.core.utils.ApiDocumentUtils.getDocumentRequest;
import static com.allgoyou.core.utils.ApiDocumentUtils.getDocumentResponse;
import static com.allgoyou.core.utils.DocumentFormatGenerator.getDateTimeFormat;
import static com.allgoyou.core.utils.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.allgoyou.core.base.BaseControllerTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

public class ${DomainClass}RestDocControllerTest extends BaseControllerTest {
@BeforeEach
static void init() {

    }

@AfterEach
    void tearDown() {
        }

@Test
@DisplayName("알뜰장 생성")
    void create${DomainClass}() throws Exception {
        //given
        MarketDto

        //when


        //then

        }

@Test
@DisplayName("조회")
public void find${DomainClass}() throws Exception {
    //given

    //when

    //then

    }

@Test
@DisplayName("검색")
public void find${DomainClass}s() throws Exception {
    //given

    //when

    //then
    }

@Test
@DisplayName("수정")
    void update${DomainClass}() throws Exception {
        //given

        //when

        //then
        }

@Test
@DisplayName("사용자삭제")
public void delete${DomainClass}() throws Exception {
    //given

    //when

    //then
    }
    }
