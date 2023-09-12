alter table tab_payment_ways add updated_at timestamp null;
update tab_payment_ways set updated_at = current_timestamp at time zone 'utc';
alter table tab_payment_ways alter column updated_at set not null;
