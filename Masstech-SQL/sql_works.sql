create database august_batch;

use august_batch;

create table emp (

eid int  primary key auto_increment,
ename varchar(100),
email varchar(150),
esalary decimal(9,4) default(0)

);



-- alter table emp modify column esalary double default(10000);

show tables;
desc emp;

DELIMITER $$
create procedure addEmpProc(
    IN name VARCHAR(100),
    IN email VARCHAR(150),
    IN salary DECIMAL(9,4)
)
begin 

insert into emp (ename,email,esalary) values (name,email,salary);

end $$

DELIMITER ;

DELIMITER $$
create procedure FetchEmpProc()
begin 
select * from emp;
end $$
DELIMITER ;

DELIMITER $$
create procedure deleteEmpProc( in id int )
begin 
delete from emp where eid=id;
end $$
DELIMITER ;

DELIMITER $$
create procedure updtEmpProc( 
    IN id int,
    IN name VARCHAR(100),
    IN empemail VARCHAR(150),
    IN salary DECIMAL(9,4) )
begin 
update emp set ename=name,email=empemail,esalary=salary where eid=id;
end $$
DELIMITER ;

call addEmpProc("arbaaz","arbaaz@gmail.com",23006);
call FetchEmpProc();
call deleteEmpProc(5);
call updtEmpProc(10,"md arbaaz","md.rocks788@gmail.com",45000);





DELIMITER $$

CREATE PROCEDURE ManageEmployee (
    IN input_id INT,
    IN input_name VARCHAR(100),
    IN input_email VARCHAR(150),
    IN input_salary DECIMAL(9,4),
    IN choice INT
)
BEGIN
    IF choice = 1 THEN
        -- Insert (auto-increment eid, so omit it)
        INSERT INTO emp (ename, email, esalary)
        VALUES (input_name, input_email, input_salary);

    ELSEIF choice = 2 THEN
        -- Read
        SELECT * FROM emp;

    ELSEIF choice = 3 THEN
        -- Update
        UPDATE emp
        SET ename = input_name,
            email = input_email,
            esalary = input_salary
        WHERE eid = input_id;

    ELSEIF choice = 4 THEN
        -- Delete
        DELETE FROM emp WHERE eid = input_id;

    ELSE
        SELECT 'Invalid choice. Use 1 (Insert), 2 (Read), 3 (Update), 4 (Delete).';
    END IF;
END $$

DELIMITER ;


SET SQL_SAFE_UPDATES = 0;

 
CALL ManageEmployee(0, 'john', 'john@example.com', 4580.23, 1); -- Insert (ID ignored)
CALL ManageEmployee(0, '', '', 0, 2); -- Read all
CALL ManageEmployee(1, 'Mohammed Arbaaz', 'md.arbaaz@gmail.com', 85000.5678, 3); -- Update
CALL ManageEmployee(1, '', '', 0, 4); -- Delete





 
call AddEmp(1200,"John","john@gmail.com");


DELIMITER $$ 
CREATE PROCEDURE FetchRecords ()
begin

SELECT * FROM emp;

end $$
DELIMITER ; 

call FetchRecords();


DELIMITER $$ 
CREATE PROCEDURE Calculate_Salary(
IN empid int 
)
begin 

declare gSalary decimal(9,2);
declare pf decimal(9,2);
declare netSalary decimal(9,2);


select esalary into gSalary from emp where eid=empid;

set pf=gSalary * 0.1;
set netSalary =gSalary - pf;

select gSalary as Gross_Salary, pf as Pf_Ammount, netSalary as Net_Salary;

end $$
select * from emp; 
call Calculate_Salary(1);



DELIMITER $$ 
CREATE PROCEDURE CalNetSalary(
IN empid int ,
OUT netSalary decimal(9,2),
OUT gSalary decimal(9,2),
OUT pf decimal(9,2),
OUt empName varchar(100)
)
begin 

select esalary into gSalary from emp where eid=empid;
select ename into empName from emp where eid=empid;
set pf=gSalary * 0.1;
set netSalary =gSalary - pf;

end $$

set @netSalary=0;
set @gSalary=0;
set @pf=0;
set @empName=null;

call CalNetSalary(5,@netSalary,@gSalary, @pf,@empName);
select @empName as Emp_Name, @netSalary as Net_Salary,@gSalary as Gross_Salary, @pf as PF ; 
select * from emp;

-- joins 

-- Create the customer table
CREATE TABLE customer (
    cid INT PRIMARY KEY,
    cname VARCHAR(50),
    cadd VARCHAR(100)
);

-- Insert some sample data
INSERT INTO customer (cid, cname, cadd) VALUES
(101, 'Aarav Mehta', 'Mumbai, Maharashtra'),
(102, 'Reena Patel', 'Ahmedabad, Gujarat'),
(103, 'Ishaan Roy', 'Kolkata, West Bengal'),
(104, 'Meera Khan', 'Bhopal, Madhya Pradesh'),
(105, 'Yash Verma', 'Jaipur, Rajasthan');

INSERT INTO customer (cid, cname, cadd) VALUES
(106, 'Arbaaz Alam', 'Mumbai, Maharashtra'),(107, 'Ritu Anasri', 'Ahmedabad');

select * from customer;


-- Create the product table
CREATE TABLE product (
    pid INT PRIMARY KEY,
    pname VARCHAR(100),
    price DECIMAL(10,2),
    cid INT,
    FOREIGN KEY (cid) REFERENCES customer(cid)
);

INSERT INTO product (pid, pname, price, cid) VALUES
(201, 'Wireless Mouse', 899.00, 101),
(202, 'Bluetooth Headphones', 1599.00, 102),
(203, 'Smartwatch', 2499.00, 103),
(204, 'Gaming Keyboard', 1299.00, 104),
(205, 'Portable Charger', 799.00, 105);


-- INSERT INTO product (pid, pname, price, cid) VALUES
-- (206, 'Wireless Mouse', 899.00, 106); throws error as 206 is not present 

-- now adding foreign key to an existing table 

-- Initial table creation
CREATE TABLE payment (
    payment_id INT PRIMARY KEY,
    amount DECIMAL(10,2)
);

drop table payment;

desc payment;

-- 1 Step
alter table payment add pid int ;
alter table payment add cid int ;

-- 2 step
alter table payment add constraint pro_fk foreign key (pid) references product(pid);
alter table payment add  foreign key (pid) references product(pid);

alter table payment add constraint cus_fk foreign key (cid) references customer(cid);

-- Inserting sample payment data
INSERT INTO payment (payment_id, amount, pid, cid) VALUES
(501, 899.00, 201, 101),   
(502, 1599.00, 202, 102),  
(503, 2499.00, 203, 103), 
(504, 1299.00, 204, 104), 
(505, 799.00, 205, 105);  

select * from payment;

-- inner join

select cname,pname,price from customer inner join product on customer.cid=product.cid;

-- left outer join 
select cname,pname from customer left join product on customer.cid=product.cid;

-- right outer join
select pname,cname from customer left join product on customer.cid=product.cid;

select * from customer;

create clustered index myClusteredIndex on customer(id);

SHOW INDEX FROM customer;

create table employee(
id int primary key,
username varchar(100) unique,
password varchar(100)
);

INSERT INTO employee (id, username, password) VALUES
(1, 'jaya.dev', 'passJaya123'),
(2, 'naveen.tech', 'naveenPwd!98'),
(3, 'amrita.hr', 'amritaSecure@78'),
(4, 'rahul.ops', 'RahulOps2023'),
(5, 'sneha.admin', 'SnehaAdmin#45');

select * from employee;
   _index on employee(username);
SHOW INDEX FROM employee;


show tables;
select * from emp;

create table emplog(
eid int primary key ,
ename varchar(100),
email varchar(150),
esalary decimal(9,4) default(0),
created_at date,
created_by varchar(100)
);



-- creating triggers
-- After trigger
-- Afteer insert

DELIMITER $$ 
create trigger after_insert_emp 
    after insert on emp
    for each row 
    begin
    
    insert into emplog (eid,ename,email,esalary,created_at,created_by,operation)
            values (new.eid,new.ename,new.email,new.esalary,now(),"ADMIN","NEW");
    
    end $$
    DELIMITER ;



    
    show triggers;
    
   
    
    INSERT INTO emp (ename, email, esalary)
        VALUES ("John","john@gmail.com",15000);

select * from emplog;
select * from emp;
    
    
    -- creating triggers for after delete 
    DELIMITER $$ 
create trigger after_delete_emp 
    after delete on emp
    for each row 
    begin
            
            update emplog set ename=old.ename,email=old.email,esalary=old.esalary,operation="DELETE",
            deleted_by="USER",deleted_at=current_timestamp() where eid=old.eid;
            
    
    end $$
    DELIMITER ;
 
 
   
    alter table emplog add  operation varchar(100);
    
   
    show triggers;
    select * from emp;
    select * from emplog;
   
    delete from emp where eid =3;
    
    
    DELIMITER $$ 
create trigger after_update_emp 
    after update on emp
    for each row 
    begin
    
    update emplog set ename=new.ename, email=new.email, esalary=new.esalary, 
    operation="UPDATED", modified_time=current_timestamp(), modified_by="USER"
    where eid=new.eid  ;
		
    end $$
    DELIMITER ;
    
   
    update emp set ename="arbaaz", email="arbaaz@gmail.com" where eid=3;
    select * from emp;
    select * from emplog;
    
    show triggers;
    alter table emplog add  modified_by varchar(100), add modified_time datetime; 
    
    alter table emplog add  deleted_by varchar(100), add deleted_at datetime; 
    
    -- creating before trigger
    
    DELIMITER $$
    create trigger before_insert_salary 
      before insert on emp
       for each row 
	begin
    if new.esalary <10000 then
    
    signal sqlstate '45000'
    set message_text="salary must be more then 10000";
    end if;
    end $$
    DELIMITER ; 
    select * from emp;
    insert into emp (ename,email,esalary) values ("John","john@gmail.com",10001);
    

    
    select * from emp;
    
    
    
    
