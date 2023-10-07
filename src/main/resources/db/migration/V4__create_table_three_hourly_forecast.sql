create table three_hourly_forecast (
       id bigserial not null,
        temp double,
        date_time varchar(255),
        five_days_forecast_id bigint not null references five_days_forecast (id),
        primary key (id)
    );