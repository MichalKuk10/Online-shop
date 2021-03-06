DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS discount_codes;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email character varying(255) CONSTRAINT proper_email CHECK (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$') UNIQUE NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    phone_number character varying(100),
    address character varying(255),
    user_role character varying(255),
    newsletter_subscription BOOLEAN
);

CREATE TABLE product_categories (
    product_category_id SERIAL PRIMARY KEY,
    "name" character varying(255) NOT NULL
);

CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_category_id integer,
    CONSTRAINT krzysztof FOREIGN KEY (product_category_id) REFERENCES product_categories(product_category_id), 
    "name" character varying(255) NOT NULL,
    brand_name character varying(255) NOT NULL,
    price FLOAT(4) CONSTRAINT positive_number CHECK (price>0),
    age_category character varying(255)
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    "date" DATE NOT NULL DEFAULT CURRENT_DATE,
	user_id integer,
	delivery_method_cost FLOAT(4),
	discount_value FLOAT(4),
	CONSTRAINT usersFK FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE order_products (
    order_product_id SERIAL PRIMARY KEY,
    quantity integer CONSTRAINT positive_number CHECK (quantity > 0) NOT NULL ,
	order_id integer,
	CONSTRAINT ordersFK FOREIGN KEY (order_id)  REFERENCES orders(order_id),
    product_id integer ,
	CONSTRAINT productsFK FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE discount_codes (
    discount_code_id SERIAL PRIMARY KEY,
    discount_code character varying(255) NOT NULL
);

insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('admin@admin.pl', 'admin1', 'Jenson', 'Patel', '27345961', 'Bramblewick, Colsterworth Road, Skillington NG33 5HF', 'admin', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Felix.Wilkinson@gmail.com', 'Ol9h?3!K', 'Felix', 'Wilkinson', '31469275', '17 Fincham Close, Stockton-On-Tees TS20 1RJ', 'admin', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Reuben.Brooks@gmail.com', '3e4@LS?r', 'Reuben', 'Brooks', '58943762', '48 Birks Holt Drive, Maltby S66 7JZ', 'admin', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Maximilian.Reid@gmail.com', '<?M14Sfc', 'Maximilian', 'Reid', '91865423', '3 Lilleshall Way, Stafford ST17 9FD', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Tomas.Rees@gmail.com', 'G<pF1r%4', 'Tomas', 'Rees', '35946172', '2 Royston Close, Hertford SG14 1NJ', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Eric.Cooke@gmail.com', '?42zSO!k', 'Eric', 'Cooke', '25319468', '5 Church Walk, Farnworth BL4 7DS', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Simon.Hamilton@gmail.com', 'n3uLM?<8', 'Simon', 'Hamilton', '49182637', '30 Mount Gould Road, Plymouth PL4 7PT', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Ellis.Mason@gmail.com', '?7lrN%5Z', 'Ellis', 'Mason', '73984261', '29 Premier Way, Romsey SO51 9DQ', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Simon.Gill@gmail.com', 'u37!Bi&E', 'Simon', 'Gill', '49812376', '79 Glastonbury Road, Stretford M32 9PF', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Miles.Barrett@gmail.com', 'U!9j&Gl8', 'Miles', 'Barrett', '26781539', '1 Karen Close, Benfleet SS7 1NT', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Ethan.Wilkinson@gmail.com', '!eZEp3?7', 'Ethan', 'Wilkinson', '83217469', '179A Church Road, London NW10 9EE', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Travis.Wallace@gmail.com', '6!>jhS2F', 'Travis', 'Wallace', '61497328', '1 West Trenethick Farm, Helston TR13 0BT', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Xander.Jones@gmail.com', '@N$3kEz9', 'Xander', 'Jones', '13258469', '73 Park View Road, Sutton Coldfield B74 4PR', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Rupert.Ellis@gmail.com', 'fP1u3?I>', 'Rupert', 'Ellis', '25419763', '6 Belfry Avenue, Harefield UB9 6HY', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Zach.Turner@gmail.com', '<p4tEW9&', 'Zach', 'Turner', '15628439', 'Ty Iago, Llanbedrog LL53 7TS', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Miller.Spencer@gmail.com', 'an68W@M>', 'Miller', 'Spencer', '76854912', 'Brunswick House, 37 - 51 Brunswick Street, Luton LU2 0HF', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Elliott.Gardner@gmail.com', '@4Af2<pK', 'Elliott', 'Gardner', '61893752', '24 Vicarage Crescent, Kidderminster DY10 1ND', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Joseph.West@gmail.com', 'PW9<o?z2', 'Joseph', 'West', '74518923', 'Hey Farm Cottage, Manchester Road, Barnoldswick BB18 5PW', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Morgan.Clarke@gmail.com', '$9<S2sLk', 'Morgan', 'Clarke', '61354987', '58 Townend Lane, Deepcar S36 2TS', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Denis.White@gmail.com', '!r?8IL9h', 'Denis', 'White', '83612597', '43 Eyre Lane, Sheffield S1 4RB', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Beau.Shaw@gmail.com', 'A24fbK$>', 'Beau', 'Shaw', '21794638', 'Albury Heights, White Lane, Guildford GU4 8PR', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Parker.Campbell@gmail.com', 'R>@u9w3A', 'Parker', 'Campbell', '93762541', '90 Crossfield Avenue, Cowes PO31 8EW', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Billy.Mills@gmail.com', '8opA2!G@', 'Billy', 'Mills', '24589631', '1 Brooklands, Redditch B98 9DW', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Nicholas.Grant@gmail.com', '?Wht2R&9', 'Nicholas', 'Grant', '57436982', '74 Hewett Road, Dagenham RM8 2XS', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Luca.Murray@gmail.com', 'u?k1BO@3', 'Luca', 'Murray', '61243978', 'Flat 1, 113 Ullet Road, Liverpool L17 2AB', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Arlo.Marsh@gmail.com', '7h!p%BH1', 'Arlo', 'Marsh', '14627589', '18 Sansome Mews, Worcester WR1 1PL', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Albert.Young@gmail.com', 'KPh%d$79', 'Albert', 'Young', '13659724', 'Flat 2, Royal Court, Marine Hill, Clevedon BS21 7NX', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Benedict.Hamilton@gmail.com', 'Su2Ne6#?', 'Benedict', 'Hamilton', '17468325', 'Thatched Cottage, Chapel Hill, Blunsdon SN26 7BL', 'customer', true);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Aston.Francis@gmail.com', '@<DI76js', 'Aston', 'Francis', '35194628', 'Greenhills Lodge, Stafford Lane, Codsall WV8 2HN', 'customer', false);
insert into users (email, password, first_name, last_name, phone_number, address, user_role, newsletter_subscription) values ('Ernest.Watts@gmail.com', 'oc&J$O45', 'Ernest', 'Watts', '23549867', '1 Y Gorlan, Goodwick SA64 0BB', 'customer', true);

insert into product_categories (name) values ('baby toys');
insert into product_categories (name) values ('cuddly toys');
insert into product_categories (name) values ('crafts and drawing');
insert into product_categories (name) values ('dolls');
insert into product_categories (name) values ('building blocks');
insert into product_categories (name) values ('figures & play sets');
insert into product_categories (name) values ('games');
insert into product_categories (name) values ('outdoortoys');
insert into product_categories (name) values ('electronics');
insert into product_categories (name) values ('party');

insert into products (product_category_id, name, brand_name, age_category, price) values (1, 'Teetcher for babies', 'Vtech', '1', 16.95);
insert into products (product_category_id, name, brand_name, age_category, price) values (1, 'Ball pool', 'Studio 100', '2', 21.99);
insert into products (product_category_id, name, brand_name, age_category, price) values (1, 'My first steering wheel', 'Vtech', '2', 19.90);
insert into products (product_category_id, name, brand_name, age_category, price) values (2, 'Plush teddy bear with heart', 'LG-Imports', '2', 6.95);
insert into products (product_category_id, name, brand_name, age_category, price) values (3, 'Watercolour set', 'Grafix', '5', 1.50);
insert into products (product_category_id, name, brand_name, age_category, price) values (3, 'Clay set (12 pieces)', 'Free and easy', '6', 1.20);
insert into products (product_category_id, name, brand_name, age_category, price) values (4, 'Barbie doll', 'Barbie', '6', 23.95);
insert into products (product_category_id, name, brand_name, age_category, price) values (4, 'Baby doll', 'Vtech', '4', 21.95);
insert into products (product_category_id, name, brand_name, age_category, price) values (5, 'LEGO lunch set', 'LEGO', '8', 9.95);
insert into products (product_category_id, name, brand_name, age_category, price) values (5, '3D wooden house set', 'Creotime', '12', 13.65);
insert into products (product_category_id, name, brand_name, age_category, price) values (6, 'Farm animals (20 figures)', 'Johntoy', '10', 7.35);
insert into products (product_category_id, name, brand_name, age_category, price) values (6, 'Running Dinosaur', 'Toi-Toys', '7', 12.95);
insert into products (product_category_id, name, brand_name, age_category, price) values (7, 'Monopoly', 'Hasbro', '12', 30);
insert into products (product_category_id, name, brand_name, age_category, price) values (7, 'Playing cards set', 'LG-Imports', '8', 1);
insert into products (product_category_id, name, brand_name, age_category, price) values (8, 'Star bubbleblow ', 'Free and easy', '6', 2);
insert into products (product_category_id, name, brand_name, age_category, price) values (8, 'Football ', 'TOM', '5', 8.5);
insert into products (product_category_id, name, brand_name, age_category, price) values (9, 'Electronic keyboard', 'Bontempi ', '9', 23);
insert into products (product_category_id, name, brand_name, age_category, price) values (9, 'My first computer', 'Hape', '8', 12);
insert into products (product_category_id, name, brand_name, age_category, price) values (10, 'Party hat', 'LG-Imports', '5', 2);
insert into products (product_category_id, name, brand_name, age_category, price) values (10, 'Balloon set (8 baloons)', 'Free and easy', '8', 5);

insert into orders (user_id, date) values (12, '2019-01-15');
insert into orders (user_id, date) values (8, '2020-02-14');
insert into orders (user_id, date) values (26, '2020-03-13');
insert into orders (user_id, date) values (11, '2019-04-12');
insert into orders (user_id, date) values (25, '2019-05-11');
insert into orders (user_id, date) values (15, '2018-06-10');
insert into orders (user_id, date) values (18, '2020-07-09');
insert into orders (user_id, date) values (28, '2019-08-08');
insert into orders (user_id, date) values (23, '2020-09-07');
insert into orders (user_id, date) values (26, '2018-10-06');

insert into order_products (order_id, product_id, quantity) values (1, 7, 1);
insert into order_products (order_id, product_id, quantity) values (1, 6, 2);
insert into order_products (order_id, product_id, quantity) values (1, 10, 2);
insert into order_products (order_id, product_id, quantity) values (2, 13, 3);
insert into order_products (order_id, product_id, quantity) values (2, 18, 2);
insert into order_products (order_id, product_id, quantity) values (2, 6, 3);
insert into order_products (order_id, product_id, quantity) values (3, 19, 1);
insert into order_products (order_id, product_id, quantity) values (3, 12, 3);
insert into order_products (order_id, product_id, quantity) values (3, 14, 3);
insert into order_products (order_id, product_id, quantity) values (4, 2, 3);
insert into order_products (order_id, product_id, quantity) values (4, 17, 3);
insert into order_products (order_id, product_id, quantity) values (4, 6, 2);
insert into order_products (order_id, product_id, quantity) values (5, 15, 2);
insert into order_products (order_id, product_id, quantity) values (5, 12, 1);
insert into order_products (order_id, product_id, quantity) values (5, 18, 3);
insert into order_products (order_id, product_id, quantity) values (6, 16, 3);
insert into order_products (order_id, product_id, quantity) values (6, 4, 2);
insert into order_products (order_id, product_id, quantity) values (6, 13, 2);
insert into order_products (order_id, product_id, quantity) values (7, 4, 1);
insert into order_products (order_id, product_id, quantity) values (7, 10, 1);
insert into order_products (order_id, product_id, quantity) values (7, 16, 1);
insert into order_products (order_id, product_id, quantity) values (8, 4, 1);
insert into order_products (order_id, product_id, quantity) values (8, 11, 2);
insert into order_products (order_id, product_id, quantity) values (8, 2, 3);
insert into order_products (order_id, product_id, quantity) values (9, 18, 3);
insert into order_products (order_id, product_id, quantity) values (9, 4, 2);
insert into order_products (order_id, product_id, quantity) values (9, 18, 2);
insert into order_products (order_id, product_id, quantity) values (10, 19, 1);
insert into order_products (order_id, product_id, quantity) values (10, 12, 2);
insert into order_products (order_id, product_id, quantity) values (10, 14, 3);

insert into discount_codes (discount_code) values ('dupa1');
insert into discount_codes (discount_code) values ('dupa2');
insert into discount_codes (discount_code) values ('dupa3');
