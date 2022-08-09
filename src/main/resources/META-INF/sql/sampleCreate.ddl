create table address (address_id bigint not null auto_increment, city varchar(255), country varchar(255), state varchar(255), street varchar(255), street_number varchar(255), zipCode varchar(255), primary key (address_id)) type=MyISAM
create table brand (brand_id bigint not null auto_increment, brand varchar(255), primary key (brand_id)) type=MyISAM
create table contract_status (contract_status_id bigint not null auto_increment, contract_status varchar(255), primary key (contract_status_id)) type=MyISAM
create table customer (customer_id bigint not null auto_increment, first_name varchar(255), last_name varchar(255), customer_address bigint, primary key (customer_id)) type=MyISAM
create table deal (deal_id bigint not null auto_increment, contract_number varchar(255), date_created date, lead_id varchar(255), seller_id varchar(255), user_id varchar(255), contract_status bigint, customer bigint, dealer_id bigint, vehicle bigint, primary key (deal_id)) type=MyISAM
create table dealer (dealer_id bigint not null auto_increment, dealer_name varchar(255), dealer_address bigint, primary key (dealer_id)) type=MyISAM
create table emission_standard (emission_standard_id bigint not null auto_increment, emission_standard varchar(255), primary key (emission_standard_id)) type=MyISAM
create table vehicle (vehicle_id bigint not null auto_increment, vehicle_model varchar(255), model_series varchar(255), production_year date, vehicle_value double precision, emission_standard bigint, status bigint, type bigint, vehicle_brand bigint, primary key (vehicle_id)) type=MyISAM
create table vehicle_option (vehicle_option_id bigint not null auto_increment, description varchar(255), name varchar(255), price double precision, supplier varchar(255), deal_id bigint, primary key (vehicle_option_id)) type=MyISAM
create table vehicle_status (vehicle_status_id bigint not null auto_increment, vehicle_status varchar(255), primary key (vehicle_status_id)) type=MyISAM
create table vehicle_type (vehicle_type_id bigint not null auto_increment, vehicle_type varchar(255), primary key (vehicle_type_id)) type=MyISAM
alter table customer add constraint FKnsdmvhh8grpnemx8wadofbicl foreign key (customer_address) references address (address_id)
alter table deal add constraint FKg7815jx614t8wevub4uvy2jy8 foreign key (contract_status) references contract_status (contract_status_id)
alter table deal add constraint FKra4pswv5ghcfvs8o2764mye6x foreign key (customer) references customer (customer_id)
alter table deal add constraint FK1gdva71iu74ajhwbyuptny3jb foreign key (dealer_id) references dealer (dealer_id)
alter table deal add constraint FK6spsolhsc2jguacncq8hs7abm foreign key (vehicle) references vehicle (vehicle_id)
alter table dealer add constraint FKbfh6ystvwcmqqthwewgodbrin foreign key (dealer_address) references address (address_id)
alter table vehicle add constraint FKpwx7ktmc8arfgwopebtgt2ewr foreign key (emission_standard) references emission_standard (emission_standard_id)
alter table vehicle add constraint FKj8qcbiut3611elnx70su4d67n foreign key (status) references vehicle_status (vehicle_status_id)
alter table vehicle add constraint FKqbg6qbb63gp20boyjilctr9v foreign key (type) references vehicle_type (vehicle_type_id)
alter table vehicle add constraint FKsmmnb7q6soahkh30hipqhsr9r foreign key (vehicle_brand) references brand (brand_id)
alter table vehicle_option add constraint FKpf7uthxnvixcsfog20aejmpj3 foreign key (deal_id) references deal (deal_id)
