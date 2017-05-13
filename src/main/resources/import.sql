insert into users (id, email, password) values ('1', 'test', '$2a$06$mFRTNl0m5Tp0tc57vb5mFeta98fr0t5X2aeL9mpjvxGlftwv6Z3Ji');

insert into passport (id, series, number, date, where_) values ('1', 'НМ', 232432, DATE '2011-11-11', 'Київським РВУМВС України');

insert into address (id, city, street, home_number, apartment_number) values ('1', 'Київ', 'Металістів', 2, 90);

insert into patient (id, first_name, last_name, date_of_birth, gender, doctor_id, passport_id, address_id) values ('1', 'Іван', 'Іванов', '22-11-2000', 'MALE', '1', '1', '1');

insert into patient_card (id, date, workplace, complaint, diagnosis, related_diseases, visual_inspection, patient_id) values ('1', DATE '2011-11-11', 'Робоче місце', 'Жалоба', 'gdfg', 'fdsfsd', 'gfdg', '1');

insert into event (id, name, date, start_time, end_time, color, doctor_id) values ('1', 'event1', DATE '2017-05-01', '11:00', '12:00', 'red', '1');
insert into event (id, name, date, start_time, end_time, color, doctor_id) values ('2', 'event2', DATE '2017-05-02', '11:00', '12:00', 'red', '1');