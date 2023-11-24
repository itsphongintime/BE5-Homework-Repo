SELECT * FROM cinema_be5.film;
SELECT * FROM cinema_be5.screening;
SELECT * FROM cinema_be5.room;
SELECT * FROM cinema_be5.seat;
SELECT * FROM cinema_be5.customer;
SELECT * FROM cinema_be5.reserved_seat;
SELECT * FROM cinema_be5.booking;

# Show film name ONLY which has screening.
SELECT name  
FROM film join screening 
on  film.id = screening.film_id;

# Show Room name with all seat row and seat column of "Room 2"
SELECT room.name, seat.row, seat.number 
FROM room join seat 
on  room.id = seat.room_id
where room.name = 'Room 2';

# Show all Screening Infomation including film name, room name, time of film "Tom&Jerry"
SELECT film.name, room.name, screening.start_time
FROM screening 
join room on room.id = screening.room_id
join film on film.id = screening.film_id where film.name = 'Tom&Jerry';

# Show what seat that customer "Dung Nguyen" booked
SELECT customer.first_name, customer.last_name, room.name, seat.row, seat.number, film.name, screening.start_time
FROM booking 
join reserved_seat on booking.id = reserved_seat.booking_id
join seat on seat.id = reserved_seat.seat_id
join screening on booking.screening_id = screening.id
join room on room.id = screening.room_id
join film on film.id = screening.film_id
join customer on booking.customer_id = customer.id
where customer.first_name = 'Dung';

# How many film that showed in 24/5/2022
SELECT COUNT(*)
FROM screening
WHERE DATE(start_time) = '2022-05-24';

# What is the maximum length and minumum length of all film
select min(length_min), max(length_min) from film;

# How many seat of Room 7
SELECT COUNT(*)
FROM room join seat
on room.id = seat.room_id
where room.name = 'Room 7';

# Total seat are booked of film "Tom&Jerry"
SELECT Count(*)
FROM booking 
join reserved_seat on booking.id = reserved_seat.booking_id
join screening on booking.screening_id = screening.id
where screening.film_id = 1;