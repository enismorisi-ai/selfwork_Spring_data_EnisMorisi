insert into authors(firstname, lastname, email) value
("Enis","Morisi", "enmo@gmail.com");

insert into authors (firstname,lastname,email) value
("Luca", "Campolunghi", "lucampo@gmail.com");


insert into posts (title, body, publish_date, author_id)
select "Lorem ipsum ...", "Ciao",null, id
from authors
where firstname = "Enis" 
and lastname = "Morisi";

insert into posts(title, body, publish_date, author_id)
select "Lorem ipsum...", "Non sono ciao", null,id
from authors
where firstname = "Luca"
and lastname = "Campolunghi";

insert into comments(email, body, date, post_id) value
("enmo@gmail.com", "quo usque tandem abutere", "20260517", 5),
("enmo@gmail.com", "Ego quoque latine loquor", "20260517", 8),
("idk@gmail.com", "Sempre caro mi fu quest'ermo colle", "20010117", 8)
;
