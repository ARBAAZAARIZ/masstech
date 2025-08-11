use august_batch;

create table oauth (
id int primary key auto_increment,
username varchar(100),
passsword varchar(100),
email varchar(100),
role varchar(50)
);



DELIMITER $$

CREATE PROCEDURE sign_up (
    IN o_username VARCHAR(100),
    IN o_password VARCHAR(100),
    IN o_mail VARCHAR(100),
    IN o_role VARCHAR(50)
)
BEGIN
    DECLARE cnt INT DEFAULT 0;

    SELECT COUNT(*) INTO cnt 
    FROM oauth 
    WHERE username = o_username OR email = o_mail;

    IF cnt > 0 THEN 
        SELECT "Username or Email is already present" AS msg;
    ELSE
        INSERT INTO oauth (username, passsword, email, role) 
        VALUES (o_username, o_password, o_mail, o_role);

        SELECT "Thanks for Signing up!!" AS msg;
    END IF;
END $$

DELIMITER ;

select * from oauth;


call sign_up("Jhonyjt77","jhon1l234","jhon@kgmail.comjd","ADMIN");





DELIMITER $$

CREATE PROCEDURE login (
    IN choice INT,
    IN o_username VARCHAR(100),
    IN o_passsword VARCHAR(100)
)
BEGIN
    DECLARE pass VARCHAR(100);
    DECLARE uname VARCHAR(100);
    DECLARE urole VARCHAR(50);
    DECLARE msg VARCHAR(100);

    IF choice = 1 THEN
        SELECT passsword INTO pass FROM oauth WHERE username = o_username;

        IF pass = o_passsword THEN
            SELECT role INTO urole FROM oauth WHERE username = o_username;
            SET uname = o_username;
            SET msg = 'Login successful';
        ELSE
            SET uname = o_username;
            SET urole = NULL;
            SET msg = 'Login Failed, Username or Password is wrong';
        END IF;

    ELSE
        SELECT passsword INTO pass FROM oauth WHERE email = o_username;

        IF pass = o_passsword THEN
            SELECT role, username INTO urole, uname FROM oauth WHERE email = o_username;
            SET msg = 'Login successful';
        ELSE
            SET uname = o_username;
            SET urole = NULL;
            SET msg = 'Sorry, wrong password or email';
        END IF;
    END IF;

    SELECT msg, uname, urole;
END $$

DELIMITER ;


call login(1,"arbaaz","1234");
drop procedure login;
select * from oauth;

