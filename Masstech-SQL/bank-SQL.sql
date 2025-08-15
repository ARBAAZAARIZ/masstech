CREATE DATABASE mtbank;

USE mtbank;

CREATE TABLE oauth (
    oauthid INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) DEFAULT 'USER'
);

CREATE TABLE bankuser (
    userid INT AUTO_INCREMENT PRIMARY KEY,
    holdername VARCHAR(100) NOT NULL,
    contact VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    FOREIGN KEY (email) REFERENCES oauth(email) 
);

DESC oauth;
DESC bankuser;



DELIMITER $$

CREATE PROCEDURE signup(
    IN uname VARCHAR(100),
    IN pass VARCHAR(100),
    IN mail VARCHAR(100),
    IN rl VARCHAR(10),
    IN hname VARCHAR(100),
    IN phone VARCHAR(15)
)
BEGIN
    DECLARE userExists INT DEFAULT 0;
    DECLARE emailExists INT DEFAULT 0;

    SELECT COUNT(*) INTO userExists FROM oauth WHERE username = uname;

    SELECT COUNT(*) INTO emailExists FROM oauth WHERE email = mail;

    IF userExists > 0 THEN
        SELECT 'Username already exists' AS message, FALSE AS status;
    ELSEIF emailExists > 0 THEN
        SELECT 'Email already registered' AS message, FALSE AS status;
    ELSE
       
        INSERT INTO oauth (username, password, email, role)
        VALUES (uname, pass, mail, rl);
 
        
        INSERT INTO bankuser (holdername, contact, email)
        VALUES (hname, phone, mail);

        SELECT 'Signup successful' AS message, TRUE AS status;
    END IF;
END$$

DELIMITER ;




CALL signup( 'arbaaz', 'arbaaz123', 'arbaaz@gmail.com', 'USER', 'MD Arbaaz Alam', '8591237781' );



select * from oauth;

select * from bankuser;

select * from oauth o inner join bankuser u where o.email=u.email; 

-- drop procedure login;

DELIMITER $$

CREATE PROCEDURE login (
    IN choice INT,
    IN uname VARCHAR(100),
    IN pass VARCHAR(100)
)
BEGIN
    DECLARE stpass VARCHAR(100);
    DECLARE mail VARCHAR(100);
  
    SET stpass = NULL, mail = NULL;
    
    IF choice = 1 THEN 
        SELECT password, email INTO stpass, mail FROM oauth WHERE username = uname;
    ELSE
        SELECT password, email INTO stpass, mail FROM oauth WHERE email = uname;
    END IF;

    
    IF stpass IS NULL THEN
        SELECT 'User not found' AS message, FALSE AS status,null as email;
    ELSEIF stpass = pass THEN
        SELECT 'Login successful' AS message, TRUE AS status, mail AS email;
    ELSE
        SELECT 'Wrong password' AS message, FALSE AS status,null as email;
    END IF;
END$$

DELIMITER ;


CALL login(2, 'arbaaz@gmail.com', 'arbaaz123');
CALL login(1, 'arbaaz', 'arbaaz123');



CREATE TABLE bank (
    accountno VARCHAR(20) PRIMARY KEY,
    accountifsc VARCHAR(20) NOT NULL,
    bankname VARCHAR(100) NOT NULL,
    branchname VARCHAR(100) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    userid INT NOT NULL,
    FOREIGN KEY (userid) REFERENCES bankuser(userid) 
);


DELIMITER $$

CREATE PROCEDURE create_bank_account_by_email (
    IN p_email VARCHAR(100),
    IN p_accountno VARCHAR(20),
    IN p_accountifsc VARCHAR(20),
    IN p_bankname VARCHAR(100),
    IN p_branchname VARCHAR(100),
    IN p_balance DECIMAL(15,2)
)
BEGIN
    DECLARE v_userid INT;

    SELECT userid INTO v_userid FROM bankuser WHERE email = p_email;

    INSERT INTO bank (accountno, accountifsc, bankname, branchname, balance, userid)
    VALUES (p_accountno, p_accountifsc, p_bankname, p_branchname, p_balance, v_userid
    );
    
END $$

DELIMITER ;


-- CALL create_bank_account_by_email('arbaaz@gmail.com','ACC1234567890','IFSC0001234','State Bank of India','Kurla Branch',5000.00);

select * from bank;



select contact from bankuser where email="arbaaz@gmail.com";



DELIMITER $$

CREATE PROCEDURE deposite_money(
    IN acc_no VARCHAR(20),
    IN if_code VARCHAR(20),
    IN bal DECIMAL(15,2)
)
BEGIN
    DECLARE pvsbal DECIMAL(15,2);
    DECLARE account_exist INT DEFAULT 0;

    
    SELECT COUNT(*) INTO account_exist
    FROM bank
    WHERE accountno = acc_no AND accountifsc = if_code;

    IF account_exist = 1 THEN
        
        SELECT balance INTO pvsbal
        FROM bank
        WHERE accountno = acc_no AND accountifsc = if_code;

       
        SET pvsbal = pvsbal + bal;

        
        UPDATE bank
        SET balance = pvsbal
        WHERE accountno = acc_no AND accountifsc = if_code;

        SELECT 'Deposit successful' AS message, TRUE AS status, pvsbal AS updated_balance;
    ELSE 
        SELECT 'Deposit Declined: Invalid Account Number or IFSC Code' AS message, FALSE AS status, NULL AS updated_balance;
    END IF;
END$$

DELIMITER ;


call deposite_money("AXIS60737","THA8591237781",450);


select * from bank;



DELIMITER $$

CREATE PROCEDURE transfer_fund(
    IN y_acc_no VARCHAR(20),
    IN y_ifsc_code VARCHAR(20),
    IN amount DECIMAL(15,2),
    IN t_acc_no VARCHAR(20),
    IN t_ifsc_code VARCHAR(20)
)
BEGIN
    DECLARE y_pv_bal DECIMAL(15,2);
    DECLARE t_pv_bal DECIMAL(15,2);
    DECLARE sender_exists INT DEFAULT 0;
    DECLARE receiver_exists INT DEFAULT 0;

    
    SELECT COUNT(*) INTO sender_exists
    FROM bank
    WHERE accountno = y_acc_no AND accountifsc = y_ifsc_code;

  
    SELECT COUNT(*) INTO receiver_exists
    FROM bank
    WHERE accountno = t_acc_no AND accountifsc = t_ifsc_code;

    IF sender_exists = 1 AND receiver_exists = 1 THEN
        
        SELECT balance INTO y_pv_bal
        FROM bank
        WHERE accountno = y_acc_no AND accountifsc = y_ifsc_code;

        SELECT balance INTO t_pv_bal
        FROM bank
        WHERE accountno = t_acc_no AND accountifsc = t_ifsc_code;

       
        IF y_pv_bal >= amount THEN
            SET y_pv_bal = y_pv_bal - amount;
            SET t_pv_bal = t_pv_bal + amount;

            
            UPDATE bank
            SET balance = y_pv_bal
            WHERE accountno = y_acc_no AND accountifsc = y_ifsc_code;

            UPDATE bank
            SET balance = t_pv_bal
            WHERE accountno = t_acc_no AND accountifsc = t_ifsc_code;

            SELECT 'Transferred Successfully' AS message, 1 AS status, y_pv_bal AS available_balance;
        ELSE
            SELECT 'Transfer Declined: Insufficient Balance' AS message, 0 AS status, y_pv_bal AS available_balance;
        END IF;
    ELSE
        SELECT 'Transfer Failed: Invalid Sender or Receiver Account/IFSC' AS message, 0 AS status, NULL AS available_balance;
    END IF;
END$$

DELIMITER ;


select * from bankuser;
select * from oauth;
select * from bank;

call transfer_fund("AXIS60737","THA8591237781",31500,"SBI66747","KRL9638527419");



DELIMITER $$

CREATE PROCEDURE fetch_balance(
    IN bank_name VARCHAR(100),
    IN acc_no VARCHAR(100)
)
BEGIN
    DECLARE bal DECIMAL(15,2) ;
    SET bal=-1.0;
    
    SELECT balance INTO bal
    FROM bank
    WHERE accountno = acc_no AND bankname = bank_name;

    
    IF bal >= 0 THEN 
        SELECT 'FETCHED SUCCESSFULLY' AS message, TRUE AS status, bal AS balance;
    ELSE 
        SELECT 'FAILED TO FETCH, ACCOUNT OR BANK NAME IS WRONG' AS message, FALSE AS status, NULL AS balance;
    END IF;
END$$

DELIMITER ;

select * from bank;

 
SELECT balance 
    FROM bank
    WHERE accountno = "SBI66747" AND bankname = "SBI";

call fetch_balance("SBI","SBI66747");
-- drop procedure fetch_balance;


DELIMITER $$

CREATE PROCEDURE withdraw_money(
    IN y_acc_no VARCHAR(20),
    IN y_ifsc_code VARCHAR(20),
    IN amount DECIMAL(15,2)
)
BEGIN
    DECLARE y_pv_bal DECIMAL(15,2) DEFAULT -1.0;
    DECLARE account_exists BOOLEAN DEFAULT FALSE;

    -- Check if account exists
   
        SELECT count(*) INTO account_exists FROM bank
        WHERE accountno = y_acc_no AND accountifsc = y_ifsc_code ;

    IF account_exists THEN
        -- Fetch current balance
        SELECT balance INTO y_pv_bal
        FROM bank
        WHERE accountno = y_acc_no AND accountifsc = y_ifsc_code;

        -- Check if balance is sufficient
        IF y_pv_bal >= amount THEN
            SET y_pv_bal = y_pv_bal - amount;

            UPDATE bank SET balance = y_pv_bal WHERE accountno = y_acc_no AND accountifsc = y_ifsc_code;

            SELECT 'Withdrawal SUCCESSFUL' AS message, TRUE AS status, y_pv_bal AS balance;
        ELSE
            SELECT 'Withdrawal DECLINED due to insufficient funds' AS message, FALSE AS status, y_pv_bal AS balance;
        END IF;
    ELSE
        SELECT 'Withdrawal DECLINED: Account Number or IFSC Code is INVALID' AS message, FALSE AS status, y_pv_bal AS balance;
    END IF;
END$$

DELIMITER ;

 drop procedure withdraw_money;
 select * from bankuser;
select * from bank ;

CALL withdraw_money('SBI66747', 'KRL9638527419', 50000.00);

SELECT bankname from bank where userid =1 ;
SELECT accountno,accountifsc from bank where bankname="AXIS" and userid=1;


DELIMITER $$

CREATE PROCEDURE get_bankname_by_userid(
    IN p_userid INT
)
BEGIN
    SELECT bankname
    FROM bank
    WHERE userid = p_userid;
END$$

DELIMITER ;

select userid from bankuser where email="arbaaz@gmail.com";

DELIMITER $$

CREATE PROCEDURE get_userid_by_email(
    IN p_email VARCHAR(100)
)
BEGIN

    SELECT userid 
    FROM bankuser
    WHERE email = p_email;
    
    
END$$

DELIMITER ;

call get_userid_by_email("arbaaz@gmail.com");
call get_bankname_by_userid(1);

SELECT accountno,accountifsc from bank where bankname="AXIS" and userid=1;

DELIMITER $$

CREATE PROCEDURE get_account_details(
    IN p_bankname VARCHAR(100),
    IN p_userid INT
)
BEGIN
    SELECT accountno, accountifsc
    FROM bank
    WHERE bankname = p_bankname AND userid = p_userid;
END$$

DELIMITER ;

call get_account_details("AXIS",1);

select * from bank b inner join bankuser u on b.userid=u.userid where u.email="arbaaz@gmail.com";

select * from bank;
select * from bankuser;


