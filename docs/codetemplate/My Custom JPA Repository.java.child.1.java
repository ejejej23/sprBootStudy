#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import $PACKAGE_NAME.replace(".repository", ".${EntityClass}");
import $PACKAGE_NAME.replace(".repository", ".Q${EntityClass}");
import $PACKAGE_NAME.replace(".domain.repository", ".dto.${EntityClass}Dto");
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

#set( $capitalizeEntity = $EntityClass.substring(0,1).toLowerCase() + $EntityClass.substring(1))

    #parse("File Header.java")

/**
 * @author ${USER}
 */
@Slf4j
@Transactional(readOnly = true)
public class ${EntityClass}SupportRepositoryImpl extends QuerydslRepositorySupport implements ${EntityClass}SupportRepository {
private final JPAQueryFactory queryFactory;

public ${EntityClass}SupportRepositoryImpl(JPAQueryFactory queryFactory) {
    super(${EntityClass}.class);
    this.queryFactory = queryFactory;
}

@Override
public Page<${EntityClass}Dto.ResponseList> find${EntityClass}s(${EntityClass}Dto.Search dto, Pageable pageable) {
    BooleanBuilder builder = new BooleanBuilder();
final Q${EntityClass} ${capitalizeEntity} = Q${EntityClass}.${capitalizeEntity};

    List<${EntityClass}Dto.ResponseList> query = null;
//         query = queryFactory.select(new ${EntityClass}Dto_ResponseList(
//         query = queryFactory.select(Projections.fields(${EntityClass}Dto.ResponseList.class,
//
//            ))
//            .from(market)
//            .where(builder)
//            .fetch();
    JPQLQuery<${EntityClass}> countQuery = queryFactory.selectFrom(${capitalizeEntity})
    .where(builder);

    return PageableExecutionUtils.getPage(query, pageable, countQuery::fetchCount);
    }
}