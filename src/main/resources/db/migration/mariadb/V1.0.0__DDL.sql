create table post
(
    post_id bigint auto_increment comment '게시고유번호'
        primary key,
    post_dt timestamp(6) null  comment '게시일시',
    author  varchar(255) null comment '저자',
    content text         null comment '내용',
    title   varchar(500) not null comment '제목',
    reg_dt  datetime  not null comment '등록일시',
    upd_dt  datetime  not null comment '수정일시'
)
    comment '게시';

create table user
(
    user_id   bigint auto_increment comment '사용자고유번호'
        primary key,
    email     varchar(255) not null comment '전자우편',
    password     varchar(255) not null comment '비밀번호',
    user_name varchar(255) not null comment '사용자명',
    birthday date not null comment '생일',
    role      varchar(255) null comment '역할',
    reg_dt    datetime     not null comment '등록일시',
    upd_dt    datetime     not null comment '수정일시'
)
    comment '사용자';



