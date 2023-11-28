select * from film;
select * from screening;
select * from customer;
select * from booking;
select * from reserved_seat;
select * from room;
select * from seat;

# 1. Show film which dont have any screening
select distinct film.name, film.length_min, film.country_code, film.type from film left join screening on film.id = screening.film_id where film.id = 10;

# 2. Who book more than 1 seat in 1 booking
select distinct customer.first_name, customer.last_name from customer
join booking on customer.id = booking.customer_id
join reserved_seat on booking.id = reserved_seat.booking_id
group by customer.id
having count(reserved_seat.id) > 1;

# 3. Show room show more than 2 film in one day
select distinct room.name
from room
join (select screening.room_id, date(screening.start_time) as screening_date, COUNT(distinct screening.film_id) as film_count
from screening group by screening.room_id, date(screening.start_time)) as daily_screenings on room.id = daily_screenings.room_id
where daily_screenings.film_count > 2;

# 4. which room show the least film ?
select room.name, films_per_room.film_count
from room
join (
    select screening.room_id, COUNT(distinct screening.film_id) asfilm_count
    from screening
    group by screening.room_id
) asfilms_per_room on room.id = films_per_room.room_id
order by films_per_room.film_count asc
limit 1;

# 5. what film don't have booking
select distinct * from film
where not exists (
select * from screening
left join booking on booking.screening_id = screening.id
where screening.film_id = film.id AND booking.id IS NOT NULL);

# 6. WHAT film have show the biggest number of room?
select distinct film.*, film_room_count.room_count from film
join (select screening.film_id, count(distinct room_id) as room_count from screening group by film_id) as film_room_count
on film.id = film_room_count.film_id
order by room_count desc
limit 2;

# 7. Show number of film  that show in every day of week and order descending
select film.*, weekly_showing.different_showing_dates from film
join (select screening.film_id, count(distinct date(screening.start_time)) as different_showing_dates from screening group by film_id) as weekly_showing
on film.id = weekly_showing.film_id
where different_showing_dates = 7
order by film.id desc;

# 8. show total length of each film that showed in 28/5/2022
select distinct film.id, film.name, sum(film.length_min) as total_showing_time from film
join screening on film.id = screening.film_id
where date(screening.start_time) ='2022-05-28'
group by film.id
order by film.id asc;

# 9. What film has showing time above and below average show time of all film
# below average
select * from film
where film.length_min < (select avg(film.length_min) from film)
order by length_min desc;

# above average
select * from film
where film.length_min > (select avg(film.length_min) from film)
order by length_min desc;

# 10. what room have least number of seat?
select room.name, seat_per_room.seat_count from room 
join (select seat.room_id, count(seat.id) as seat_count from seat group by room_id) as seat_per_room
on room.id = seat_per_room.room_id
order by seat_per_room.seat_count asc
limit 1;

# 11. what room have number of seat bigger than average number of seat of all rooms
select room.name, seat_per_room.seat_count from room 
join (select seat.room_id, count(seat.id) as seat_count from seat group by room_id) as seat_per_room
on room.id = seat_per_room.room_id
where seat_per_room.seat_count > (select avg(seat_count) from (select count(seat.id) as seat_count from seat group by seat.room_id) as avg_seat_count);


# 12 Ngoai nhung seat mà Ong Dung booking duoc o booking id = 1 thi ong CÓ THỂ (CAN) booking duoc nhung seat nao khac khong?
select * from seat
join ;


# 13.Show Film with total screening and order by total screening. BUT ONLY SHOW DATA OF FILM WITH TOTAL SCREENING > 10
select * from film 
join screening 

# 14.TOP 3 DAY OF WEEK based on total booking
# 15.CALCULATE BOOKING rate over screening of each film ORDER BY RATES.
# 16.CONTINUE Q15 -> WHICH film has rate over average ?.
# 17.TOP 2 people who enjoy the least TIME (in minutes) in the cinema based on booking info - only count who has booking info (example : Dũng book film tom&jerry 4 times -> Dũng enjoy 90 mins x 4)
