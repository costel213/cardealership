create table address (address_id bigint not null auto_increment, city varchar(255), country varchar(255), number varchar(255), state varchar(255), street varchar(255), zipCode varchar(255), primary key (address_id)) type=MyISAM
create table customer (customer_id bigint not null auto_increment, first_name varchar(255), last_name varchar(255), customer_address bigint, primary key (customer_id)) type=MyISAM
create table deal (deal_id bigint not null auto_increment, contract_number varchar(255), contract_status integer, date_created date, lead_id varchar(255), seller_id varchar(255), user_id varchar(255), customer bigint, dealer_id bigint, vehicle bigint, primary key (deal_id)) type=MyISAM
create table dealer (dealer_id bigint not null auto_increment, dealer_name varchar(255), dealer_address bigint, primary key (dealer_id)) type=MyISAM
create table vehicle (vehicle_id bigint not null auto_increment, brand varchar(255), emission_standard varchar(255), model varchar(255), model_series varchar(255), production_year date, status varchar(255), type varchar(255), value double precision, primary key (vehicle_id)) type=MyISAM
alter table customer add constraint FKnsdmvhh8grpnemx8wadofbicl foreign key (customer_address) references address (address_id)
alter table deal add constraint FKra4pswv5ghcfvs8o2764mye6x foreign key (customer) references customer (customer_id)
alter table deal add constraint FK1gdva71iu74ajhwbyuptny3jb foreign key (dealer_id) references dealer (dealer_id)
alter table deal add constraint FK6spsolhsc2jguacncq8hs7abm foreign key (vehicle) references vehicle (vehicle_id)
alter table dealer add constraint FKbfh6ystvwcmqqthwewgodbrin foreign key (dealer_address) references address (address_id)
