insert into product(id, code) values(product_seq.nextval(), 'Suzuki GV');
insert into purchase(id, product_id, payment_date) values (purchase_seq.nextval(), product_seq.currval(), sysdate);
