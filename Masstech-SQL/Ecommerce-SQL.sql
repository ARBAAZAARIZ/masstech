create database byte_cart;

use byte_cart;

create table users (
    user_id int primary key auto_increment,
    fullname varchar(50) not null,
    username varchar(50) not null unique,
    password varchar(100) not null,
    email varchar(100) not null unique,
    role varchar(20) not null,
    address varchar(500),
    user_status varchar(10) default("ACTIVE")
);





create table products (
    product_id int primary key auto_increment,
    product_name varchar(100) not null,
    product_description varchar(200),
    price decimal(10,2) not null,
    stock int default 2
);


create table cart (
    cart_id int primary key auto_increment,
    user_id int not null,
    product_id int not null,
    quantity int default 1,
    product_name varchar(100),
    added_at date,
    product_price decimal(10,2),
    total_price decimal(10,2),
    foreign key (user_id) references users(user_id),
    foreign key (product_id) references products(product_id)
);


create table product_purchased (
    pro_pur_id int primary key auto_increment,
    product_id int not null,
    user_id int not null,
    product_name varchar(100),
    ordered_date date not null,
    quantity int not null,
    deliver_date date not null,
    product_price decimal(10,2),
    total_price decimal(10,2),
    foreign key (product_id) references products(product_id),
    foreign key (user_id) references users(user_id)
);

drop table product_purchased;


create table bank (
    bank_id int primary key auto_increment,
    user_id int not null,
    account_number varchar(20) not null unique,
    balance decimal(10,2) default 0.00,
    bank_name varchar(50) not null,
    foreign key (user_id) references users(user_id)
);


drop procedure login;

delimiter $$

create procedure login (
    in login_id varchar(100),
    in input_password varchar(100)
)
begin
    declare db_password varchar(100);
    declare db_username varchar(50);
    declare db_userid int;
    declare db_role varchar(20);
    declare db_email varchar(100);
    declare db_address varchar(500);
    declare db_status varchar(10);

    
    select password, username, user_id, role, email, address,user_status
    into db_password, db_username, db_userid, db_role, db_email,db_address,db_status
    from users
    where email = login_id or username = login_id ;

    if db_password = input_password then
        select 
            'login successful' as message,
            db_username as username,
            db_userid as user_id,
            db_role as role,
            db_email as email,
            db_address as address,
            db_status as user_status,
            1 as status;
    else
        select 
            'invalid credentials' as message,
            null as username,
            null as user_id,
            null as role,
            null as email,
            null as address,
            null as user_status,
            0 as status;
    end if;
end$$

delimiter ;

insert into users (fullname,username,password,email,role) values ("Sam Carran","sam","sam123","sam@gmail.com","USER");

select * from users;



call login("sam@gmail.com","sam123");



delimiter $$

create procedure signup (
	in s_fullname varchar(100),
    in s_username varchar(100),
    in s_password varchar(100),
    in s_email varchar(100),
    in s_role varchar(100),
    in addr varchar(500)
)
begin
    declare cnt int;

    -- Checking if username or email already exists
    select count(*) into cnt 
    from users 
    where username = s_username or email = s_email;

    if cnt > 0 then
        select 'username or email already exists' as msg, 0 as status;
    else
        insert into users (fullname,username, password, email, role,address)
        values (s_fullname,s_username, s_password, s_email, s_role,addr);

        select 'created successfully' as msg, 1 as status;
    end if;
end$$

delimiter ;

select * from users;

call signup("MD ARBAAZ ALAM","arbaaz","arbaaz123","arbaaz@gmail.com","ADMIN");


delimiter $$
create procedure change_password(
in existingPassword varchar(100),
in newPassword varchar(100),
in id int
)
begin
declare oldpass varchar(100);

select password into oldpass from users where user_id=id;

   if oldpass = existingPassword then 
   update users set password = newPassword where user_id=id;
   select "Password Changed Successfully" as msg, 1 as status;
   else
	select "Enter password don't match exsiting passowrd" as msg, 0 as status;
    end if;

end $$
delimiter ;




delimiter $$

create procedure add_product (
    in p_name varchar(100),
    in p_description varchar(200),
    in p_price decimal(10,2),
    in p_stock int
)
begin
    insert into products (product_name, product_description, price, stock)
    values (p_name, p_description, p_price, p_stock);

    select 'product added successfully' as message, 1 as status;
end$$

delimiter ;


call add_product('Wireless Mouse', 'Ergonomic design with 2.4GHz wireless connectivity', 799.00, 10);

select * from products;

delimiter $$
create procedure update_product(
in p_id int,
in p_price decimal(10,2),
in p_stocksint int
)
begin
-- declare pvr_stocks int ;
declare cnt int default(0);

select count(*) into cnt from products where product_id = p_id;

		if cnt >0 then 
         update  products set price = p_price, stock= stock + p_stocksint where product_id = p_id;
         
         select "Product updated Successfully" as msg, 1 as status;
         
        else 
        select "Product not found!! " as msg,0 as status;
        end if ;

end $$
delimiter ;

call update_product(4,1200,2);

select * from products;

delimiter $$

create procedure add_product_into_cart(
    in u_id int,
    in pro_id int,
    in qnt int
)
begin
    declare cnt int default 0;
    declare pice_sum decimal(10,2);
    declare pri decimal(10,2); 
    declare cart_id_exist int default 0;
    declare p_name varchar(100);
    

    -- Check if product exists
    select count(*) into cnt from products where product_id = pro_id;

    -- Check if product already in cart for this user
    select count(*) into cart_id_exist from cart where user_id = u_id and product_id = pro_id;

    if cnt > 0 and cart_id_exist = 0 then 
    
		select product_name into p_name from products where product_id = pro_id;
    
        select price into pri from products where product_id = pro_id;
        set pice_sum = pri * qnt;

        insert into cart (user_id, product_id,product_name, quantity, product_price, total_price, added_at) 
        values (u_id, pro_id, p_name, qnt, pri, pice_sum, curdate());

        select 'Added your product into cart successfully' as msg, 1 as status;

    elseif cnt > 0 then 
        select price into pri from products where product_id = pro_id;
        set pice_sum = pri * qnt;

        update cart 
		set 
		total_price = (quantity + qnt) * product_price, quantity = quantity + qnt
		where user_id = u_id and product_id = pro_id;


        select 'Cart updated successfully' as msg, 1 as status;

    else
        select 'Wrong input option' as msg, 0 as status;
    end if;
end$$

delimiter ;


call add_product_into_cart(8,5,1);

select * from users;  -- 8
select * from products;  -- 4,5
select * from cart;



delimiter $$

create procedure insert_bank_details (
    in p_user_id int,
    in p_account_number varchar(20),
    in p_balance decimal(10,2),
    in p_bank_name varchar(50)
)
begin
    declare user_exists int;
    declare bank_exists int;

    
    select count(*) into user_exists
    from users
    where user_id = p_user_id;

    if user_exists = 0 then
        select ' User ID does not exist.' as msg, 0 as status;
    else
        
        select count(*) into bank_exists
        from bank
        where user_id = p_user_id and bank_name = p_bank_name;

        if bank_exists > 0 then
            select concat(' Account already exists for bank: ', p_bank_name) as msg, 0 as status;
        else
            
            insert into bank (user_id, account_number, balance, bank_name)
            values (p_user_id, p_account_number, p_balance, p_bank_name);

            select ' Bank account created successfully.' as msg, 1 as status;
        end if;
    end if;
end $$

delimiter ;


call insert_bank_details(8,"PNB89475",60000,"PNB BANK");

select * from users;  -- 8
select * from products;  -- 4,5
select * from cart;
select * from bank;

select sum(total_price) as total_amount from cart where user_id=8;  -- for total amunt
select balance from bank where user_id = 8 and bank_name="AXIS BANK" ; -- for banace check

select* from cart where user_id=8;

select bank_name from bank where user_id=8;


delimiter $$

create procedure placeing_order(
    in c_id int,
    in u_id int,
    in p_id int,
    in p_name varchar(100),
    in qnt int,
    in p_price decimal(10,2),
    in t_price decimal(10,2),
    in b_name varchar(50)
)
begin 
    declare b_id int default 0;
    declare s_balance decimal(10,2);

    
    select bank_id, balance into b_id, s_balance  
    from bank 
    where user_id = u_id and bank_name = b_name;

    
    if s_balance >= t_price then
        -- Deduct balance
        set s_balance = s_balance - t_price;

        update bank 
        set balance = s_balance 
        where bank_id = b_id;

        
        insert into product_purchased (product_id, user_id, ordered_date, quantity, deliver_date, product_price, total_price,product_name)
				values (p_id, u_id, CURDATE(), qnt, DATE_ADD(CURDATE(), INTERVAL 7 DAY), p_price, t_price,p_name);

        
        delete from cart 
        where user_id = u_id and product_id = p_id;
        
        update products set stock=stock - qnt where product_id = p_id;

       
        select 'Product purchased successfully. It will be delivered within one week.' as msg, 1 as status;
    else
        
        select 'Insufficient balance. Please check your bank account.' as msg, 0 as status;
    end if;
end$$

delimiter ;


call placeing_order(1,8,5,"Mechanical Keyboard",6,2499.00,14994.00,"SBI BANK");

select * from users;  -- 8



select * from products;  -- 4,5
select * from cart;
select * from bank;
select * from product_purchased;

delimiter $$

create procedure depositeMoney(
    in u_id int,
    in b_name varchar(50),
    in amount decimal(10,2)
)
begin 
    declare user_exist int default 0;
    declare bank_exist int default 0;
    declare s_balance decimal(10,2);
    declare b_id int;

   
    select count(*) into user_exist from users where user_id = u_id;

    if user_exist > 0 then
        
        select count(*) into bank_exist from bank where user_id = u_id and bank_name = b_name;

        if bank_exist > 0 then
            
            select balance, bank_id into s_balance, b_id 
            from bank 
            where user_id = u_id and bank_name = b_name;

            
            if amount > 0 then
                set s_balance = s_balance + amount;

                update bank 
                set balance = s_balance 
                where bank_id = b_id;

                select ' Deposited Successfully' as msg, 1 as status,s_balance as balance;
            else
                select ' Invalid deposit amount' as msg, 0 as status,null as balance;
            end if;
        else
            select concat(' Bank account not found for ', b_name) as msg, 0 as status;
        end if;
    else 
        select ' User not found' as msg, 0 as status;
    end if;
end $$

delimiter ;

select * from bank b inner join users u on u.user_id = b.user_id;

call depositeMoney(8,"AXIS BANK",42000);


select * from bank b inner join users u on u.user_id=b.user_id;

select *   from users;
select * from products;



    
