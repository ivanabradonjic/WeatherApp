create table five_days_forecast (
       id bigserial not null,
       city_id bigint not null references city (id),
        primary key (id)
    );
