create table product (
    id numeric(19,0) not null,
    code varchar(40) not null,
    primary key(id),
    unique(code)
);
create table purchase (
    id numeric(19,0) not null,
    product_id numeric(19,0) not null,
    payment_date datetime null,
    primary key(id)
);
alter table purchase
    add constraint purchase_product_fk
    foreign key (product_id)
    references product;
create sequence product_seq;
create sequence purchase_seq;
