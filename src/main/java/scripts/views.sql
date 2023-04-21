create view choice1
    as select s.name as student, a.name as author from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
		 where a.name = 'Александр Пушкин' or a.name = 'Николай Гоголь'
or b.name = 'Вий' or b.name = 'Евгений Онегин' and s.name != 'Иван Иванов'
or s.name = 'Петр Петров'
group by 1, 2
having count(o.active) >= 1
order by s.name, a.name desc