create database vesta_db;

use vesta_db;

CREATE TABLE societies (
  society_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL UNIQUE,
  address_line1 VARCHAR(200) NOT NULL,
  address_line2 VARCHAR(200),
  city VARCHAR(80) NOT NULL,
  state VARCHAR(80) NOT NULL,
  pincode VARCHAR(10) NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME 
);

-- Members Table (basic link with Users)
CREATE TABLE members (
  member_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  society_id BIGINT,
  full_name VARCHAR(120) NOT NULL,
  email VARCHAR(150) UNIQUE,
  phone VARCHAR(20),
  status VARCHAR(20) NOT NULL DEFAULT 'Active',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (society_id) REFERENCES societies(society_id) ON DELETE CASCADE
);

desc members;

-- Users Table
CREATE TABLE users (
  user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  member_id BIGINT,
  username VARCHAR(80) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(50),
  is_active BIT NOT NULL DEFAULT 1,
  last_login_at DATETIME,
  FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE
);
select * from members;




DELIMITER $$

CREATE PROCEDURE sp_user_login(
    IN p_login_id VARCHAR(150),   -- can be username OR email
    IN p_password VARCHAR(255)    -- plain or hashed password
)
BEGIN
    SELECT 
        u.user_id,
        u.username,
        u.role,
        u.last_login_at,
        m.full_name,
        m.email,
        m.phone,
        m.status,
        m.profile_photo,
        m.society_id,
        m.member_id
    FROM users u
    JOIN members m ON u.member_id = m.member_id
    WHERE (u.username = p_login_id OR m.email = p_login_id)
      AND u.password_hash = p_password
      AND u.is_active = 1;
    
    -- Update login time if match found
    UPDATE users 
    SET last_login_at = NOW() 
    WHERE (username = p_login_id OR user_id IN (
              SELECT member_id FROM members WHERE email = p_login_id
          ))
      AND password_hash = p_password
      AND is_active = 1;
END $$

DELIMITER ;




INSERT INTO societies 
(name, address_line1, address_line2, city, state, pincode, updated_at)
VALUES
('fortune city', 'panvel, west, near railway station', NULL, 'mumbai', 'maharastra', '400012', NOW());

select * from societies;

INSERT INTO members 
(society_id, full_name, email, phone)
VALUES
(5, 'Sunita ', 'Sunita@gmail.com', '8456851186');

select * from members;

INSERT INTO users
(member_id, username, password_hash, role, last_login_at)
VALUES
(14, 'Sunita', 'sunita123', 'RESIDENT', NOW());


call sp_user_login("admin","admin1234");

select *  from members;
select * from users;

select u.user_id as userID,u.username as username, u.role as role, u.last_login_at as last_login, m.member_id as memberID,m.society_id as societyID, m.email as email, m.phone as phone_number,m.status as status from users u inner join members m on u.member_id = m.member_id ;

delete from users where user_id=4;
delete from members where member_id=8;
alter table members add column profile_photo varchar(200);

update members set profile_photo="admin_pic.png";


CREATE TABLE buildings (
  building_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  society_id BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL ,
  floors INT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (society_id) REFERENCES societies(society_id) ON DELETE CASCADE
);
select * from buildings where society_id=4;
 

CREATE TABLE flats(
  flat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  building_id BIGINT NOT NULL,
  flat_no VARCHAR(20) NOT NULL,
  floor_no INT NOT NULL,
  carpet_area_sqft DECIMAL(10,2) NOT NULL,
  is_parking_allocated BIT NOT NULL DEFAULT 0,
  FOREIGN KEY (building_id) REFERENCES buildings(building_id) ON DELETE CASCADE  
);

select * from flats;

CREATE TABLE parking_slots (
  slot_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  society_id BIGINT NOT NULL,
  identifier VARCHAR(30) NOT NULL UNIQUE,
  is_covered BIT NOT NULL DEFAULT 0,
  FOREIGN KEY (society_id) REFERENCES societies(society_id) on delete cascade
);


CREATE TABLE vehicles (
  vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  member_id BIGINT NOT NULL,
  flat_id BIGINT,
  registration_no VARCHAR(20) NOT NULL UNIQUE,
  type ENUM('TwoWheeler','FourWheeler','Other') NOT NULL,
  FOREIGN KEY (member_id) REFERENCES members(member_id) on delete cascade,
  FOREIGN KEY (flat_id) REFERENCES flats(flat_id)on delete cascade
);

select * from vehicles;
select * from parking_slots;
select flat_id from flats where  building_id=2 && flat_no="103";

select * from flats;
select * from buildings;
SELECT building_id FROM buildings WHERE name = "Fortnite";


CREATE TABLE complaints (
  complaint_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  society_id BIGINT NOT NULL,
  raised_by_user_id BIGINT NOT NULL,
  flat_id BIGINT,
  category VARCHAR(50) NOT NULL,
  title VARCHAR(150) NOT NULL,
  description TEXT,
  status ENUM('Open','In Progress','Resolved','Closed') DEFAULT 'Open',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (society_id) REFERENCES societies(society_id)on delete cascade,
  FOREIGN KEY (raised_by_user_id) REFERENCES users(user_id) on delete cascade,
  FOREIGN KEY (flat_id) REFERENCES flats(flat_id) on delete cascade
);

select * from societies;
select * from users;
select * from flats;
select * from complaints;
select * from members;

INSERT INTO complaints (society_id, raised_by_user_id, flat_id, category, title, description, status)
VALUES
(4, 12, 1, 'Plumbing', 'Leaking kitchen tap', 'Water is dripping continuously from the kitchen tap.', 'Open'),
(5, 13, 1, 'Electricity', 'Power outage in bedroom', 'No electricity in bedroom since last night.', 'In Progress'),
(5, 13, 4, 'Security', 'Gate not closing properly', 'Main gate doesn’t latch securely after closing.', 'Resolved'),
(6, 12, 4, 'Cleanliness', 'Garbage not collected', 'Garbage bins outside flat not cleared for 3 days.', 'Closed'),
(4, 13, 2, 'Noise', 'Loud music from neighbor', 'Excessive noise from adjacent flat during late hours.', 'Open');

select * from flats;
select * from users;
select * from societies;
select * from members;

select flat_id from flats where building_id=2 && flat_no=102;

CREATE TABLE notifications (
  notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  message TEXT NOT NULL,
  read_status ENUM('Unread','Read') DEFAULT 'Unread',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id) on delete cascade 
);	



select n.notification_id , n.user_id, n.message , n.read_status, n.created_at, m.full_name ,m.profile_photo, m.phone, u.username, u.role, m.society_id from notifications n
          inner join users u on n.user_id = u.user_id 
          inner join members m on u.member_id = m.member_id 
          where m.society_id = 4 and n.read_status='Unread';
          
          select count(*) from notifications n
          inner join users u on n.user_id = u.user_id 
          inner join members m on u.member_id = m.member_id 
          where m.society_id = 4 and n.read_status='unread';


select * from notifications;


INSERT INTO notifications (user_id, message)
VALUES
(16, 'Your maintenance bill for August is now available.'),
(13, 'Water supply will be interrupted tomorrow from 10 AM to 2 PM.'),
(16, 'Security gate access has been updated. Please revalidate your entry card.'),
(13, 'Society meeting scheduled for Sunday at 5 PM in the clubhouse.'),
(16, 'Your complaint regarding plumbing has been marked as resolved.'),
 (13, 'Reminder: Vehicle registration deadline is approaching.');

select * from notifications;

CREATE TABLE amenities (
  amenity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  society_id BIGINT NOT NULL ,
  name VARCHAR(80) NOT NULL,
  booking_required BIT NOT NULL DEFAULT 0,
  FOREIGN KEY (society_id) REFERENCES societies(society_id) ON DELETE CASCADE
);

alter table amenities add column amount decimal(10,2);

select * from amenities;

show tables;

CREATE TABLE gate_logs (
  gate_log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  society_id BIGINT NOT NULL,
  visitor_name VARCHAR(120) NOT NULL,
  vehicle_no VARCHAR(20),
  purpose VARCHAR(80),
  flat_id BIGINT,
  flat_no varchar(20),
  building_name varchar(100),
  check_in DATETIME NOT NULL,
  check_out DATETIME,
  FOREIGN KEY (society_id) REFERENCES societies(society_id),
  FOREIGN KEY (flat_id) REFERENCES flats(flat_id)
);

select * from flats;

DELIMITER $$

CREATE PROCEDURE insert_visitor_gatelog(
    IN societyId BIGINT,
    IN visitorName VARCHAR(120),
    IN vehicleNo VARCHAR(20),
    IN purpose VARCHAR(80),
    IN flatNo VARCHAR(20),
    IN buildingName VARCHAR(100)
)
BEGIN
    DECLARE flatId BIGINT DEFAULT NULL;
    DECLARE buildingId BIGINT DEFAULT NULL;
    DECLARE inserted BOOLEAN DEFAULT FALSE;

    -- Get building ID
    SELECT building_id INTO buildingId
    FROM buildings
    WHERE name = buildingName AND society_id = societyId;

    -- Get flat ID
    SELECT flat_id INTO flatId
    FROM flats
    WHERE building_id = buildingId AND flat_no = flatNo;

    -- Insert into gate_logs
    IF flatId IS NOT NULL THEN
        INSERT INTO gate_logs (
            society_id, visitor_name, vehicle_no, purpose,
            flat_id, flat_no, building_name, check_in
        )
        VALUES (
            societyId, visitorName, vehicleNo, purpose,
            flatId, flatNo, buildingName, NOW()
        );

        SET inserted = TRUE;
    END IF;

    -- Return result
    SELECT inserted AS success;
END $$

DELIMITER ;

select * from buildings;
select * from users;
update users set password_hash='arbaaz123' where username="arbaaz";
select * from gate_logs ;

CREATE TABLE delivery_logs (
  delivery_log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  society_id BIGINT NOT NULL,
  delivery_person_name VARCHAR(120) NOT NULL,
  mobile VARCHAR(15),
  delivery_type VARCHAR(50), -- e.g., food, parcel, grocery
  flat_id BIGINT,
  flat_no VARCHAR(20),
  building_name VARCHAR(100),
  check_in DATETIME NOT NULL,
  FOREIGN KEY (society_id) REFERENCES societies(society_id),
  FOREIGN KEY (flat_id) REFERENCES flats(flat_id)
);

DELIMITER $$

CREATE PROCEDURE insert_delivery_log(
    IN societyId BIGINT,
    IN deliveryPersonName VARCHAR(120),
    IN mobile VARCHAR(15),
    IN deliveryType VARCHAR(50),
    IN flatNo VARCHAR(20),
    IN buildingName VARCHAR(100)
)
BEGIN
    DECLARE flatId BIGINT DEFAULT NULL;
    DECLARE buildingId BIGINT DEFAULT NULL;
    DECLARE inserted BOOLEAN DEFAULT FALSE;

    -- Get building ID
    SELECT building_id INTO buildingId
    FROM buildings
    WHERE name = buildingName AND society_id = societyId;

    -- Get flat ID
    SELECT flat_id INTO flatId
    FROM flats
    WHERE building_id = buildingId AND flat_no = flatNo;

    -- Insert into delivery_logs
    IF flatId IS NOT NULL THEN
        INSERT INTO delivery_logs (
            society_id, delivery_person_name, mobile, delivery_type,
            flat_id, flat_no, building_name, check_in
        )
        VALUES (
            societyId, deliveryPersonName, mobile, deliveryType,
            flatId, flatNo, buildingName, NOW()
        );

        SET inserted = TRUE;
    END IF;

    -- Return result
    SELECT inserted AS success;
END $$

DELIMITER ;

select * from delivery_logs;
select * from members;
select * from users;
select * from amenities;
select * from societies;
update members set profile_photo="member_10_1756631980179_arbaaz pic 1.jpg" where member_id=10;


CREATE TABLE amenity_checkin_log (
    checkin_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT,                         -- FK to members table
    amenity_id BIGINT NOT NULL,               -- FK to amenities table
    amanityName varchar(80),
    checkin_time DATETIME NOT NULL  ,
    checkout_time DATETIME,                   -- null until checked out
    guard_id BIGINT NOT NULL,                 -- FK to users or guards
    society_id BIGINT NOT NULL,               -- FK to society
    status VARCHAR(20) DEFAULT 'IN',          -- IN or OUT
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (member_id) REFERENCES members(member_id) on delete cascade,
     FOREIGN KEY (amenity_id) REFERENCES amenities(amenity_id) on delete cascade,
     FOREIGN KEY (guard_id) REFERENCES users(user_id) on delete cascade,
     FOREIGN KEY (society_id) REFERENCES societies(society_id) on delete cascade  
);


select * from amenity_checkin_log;

alter table amenity_checkin_log add column amanityName varchar(120);

truncate table amenity_checkin_log;

select * from users;

create table emp (
eid int primary key auto_increment,
ename varchar(100),
esalary decimal(10,2),
mid int ,
FOREIGN KEY (mid) REFERENCES manager(mid) on delete restrict
);


create table manager(
mid int primary key auto_increment,
mname varchar(100)
);
select * from members;
select * from members;
select * from users;
select * from buildings;
select * from flats;
select * from societies;


update users set password_hash="anas123" where username="anas";

CREATE TABLE flat_occupancies (
  occupancy_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  flat_id BIGINT NOT NULL,
  member_id BIGINT NOT NULL,
  type ENUM('Owner','Tenant') NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE,
  FOREIGN KEY (flat_id) REFERENCES flats(flat_id) on delete cascade,
  FOREIGN KEY (member_id) REFERENCES members(member_id) on delete cascade
);

select * from flat_occupancies;


SELECT 
  u.user_id,
  u.username,
  u.role,
  u.last_login_at,
  u.member_id,
  m.email,
  m.phone,
  m.status,
  m.society_id,
  s.name AS society_name,
  m.profile_photo
FROM users u
JOIN members m ON u.member_id = m.member_id
JOIN societies s ON m.society_id = s.society_id
WHERE m.society_id = 5
ORDER BY u.last_login_at DESC;


CREATE TABLE parking_assignments (
  assignment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  slot_id BIGINT NOT NULL,
  vehicle_id BIGINT NOT NULL,											-- re run this tbale 
  start_date DATE NOT NULL,
  end_date DATE,
  FOREIGN KEY (slot_id) REFERENCES parking_slots(slot_id),
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

select * from members;
select * from members;
select * from users;
select * from buildings;
select * from flats;
select * from societies;
select * from vehicles;
select * from parking_slots;
show tables;

CREATE TABLE amenity_bookings (
  booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  amenity_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
amount decimal(10,2),
society_id BIGINT,
amenity_name varchar(100),                                                                      -- drop this table and re run this table 
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  status ENUM('Booked','Cancelled','Completed','Pending') DEFAULT 'Pending',
  FOREIGN KEY (amenity_id) REFERENCES amenities(amenity_id) on delete cascade,
  FOREIGN KEY (user_id) REFERENCES users(user_id) on delete cascade
);

INSERT INTO amenity_bookings (
  amenity_id, user_id, amount, society_id, amenity_name, start_time, end_time
)
VALUES (
  4, 16, 12000, 5, 'Fitness79 GYM', NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR)
);

select * from members;
select * from users;
select * from amenities;
select * from amenity_bookings;

update  amenity_bookings set status='Pending';

SELECT c.complaint_id, c.society_id, c.raised_by_user_id, u.username, c.flat_id, f.flat_no, c.category, c.title, c.description, c.status
FROM complaints c JOIN users u ON c.raised_by_user_id = u.user_id 
JOIN flats f ON c.flat_id = f.flat_id 
WHERE c.society_id = 5;

show tables;
select * from flat_occupancies o inner join flats f on f.flat_id = o.flat_id 
inner join buildings b on b.building_id = f.building_id;
select * from flats;

CREATE TABLE visitor_approval_log (
  visitor_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  visitor_name VARCHAR(100) NOT NULL,
  purpose VARCHAR(500) NOT NULL,
  flat_id BIGINT,
  member_id BIGINT,
  visitor_profile_photo VARCHAR(500),
  status ENUM('Pending','Rejected','Approved') DEFAULT 'Pending',
  FOREIGN KEY (flat_id) REFERENCES flats(flat_id) ON DELETE CASCADE,
  FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE
);

select * from visitor_approval_log;


SELECT * FROM members 
        WHERE society_id =4   AND full_name LIKE 'Anas Ansar'
        ORDER BY full_name ASC;
        
        select * from members;
        select * from users;

