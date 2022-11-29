CREATE TABLE IF NOT EXISTS Menu_Order(

	id identity primary key,
	delivery_Name varchar(50) not null,
	delivery_Street varchar(50) not null,
	delivery_City varchar(50) not null,
	delivery_State varchar(2) not null,
	delivery_Zip varchar(10) not null,
	cc_number varchar(16) not null,
	cc_expiration varchar(5) not null,
	cc_cvv varchar(3) not null,
	placed_at timestamp not null
		
);

CREATE TABLE IF NOT EXISTS Menu(

	id identity primary key,
	name varchar(50) not null,
	menu_order bigint not null,
	menu_order_key bigint not null,
	created_at timestamp not null

);

CREATE TABLE IF NOT EXISTS Ingredient_Ref(

	ingredient varchar(4) not null,
	menu bigint not null,
	menu_key bigint not null

);

CREATE TABLE IF NOT EXISTS Ingredient(

	id varchar(4) primary key not null,
	name varchar(25) not null,
	type varchar(10) not null

);



ALTER TABLE MENU
	ADD FOREIGN KEY (menu_Order) references Menu_Order(id);
	
ALTER TABLE Ingredient_Ref
	ADD FOREIGN KEY (ingredient) references Ingredient(id);

















