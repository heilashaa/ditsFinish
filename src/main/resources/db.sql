CREATE DATABASE dits CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE dits.`Answer` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `description` varchar(255) NOT NULL,
                          `correct` BOOLEAN NOT NULL,
                          `question_id` INT NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Question` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `description` varchar(255) NOT NULL,
                            `test_id` INT DEFAULT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Literature` (
                              `id` INT NOT NULL AUTO_INCREMENT,
                              `description` varchar(255) NOT NULL,
                              `question_id` INT(255) NOT NULL,
                              PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Link` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `link` varchar(255) NOT NULL,
                        `literature_id` INT NOT NULL,
                        PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Test` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `description` varchar(255) NOT NULL,
                        `topic_id` INT NOT NULL,
                        PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Topic` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `description` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Statistic` (
                             `id` INT NOT NULL AUTO_INCREMENT,
                             `date` DATETIME(6) NOT NULL,
                             `correct` BOOLEAN NOT NULL,
                             `question_id` INT NOT NULL,
                             `user_id` INT NOT NULL,
                             PRIMARY KEY (`id`)
);

CREATE TABLE dits.`User` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(255) NOT NULL,
                        `middle_name` varchar(255) NOT NULL,
                        `last_name` varchar(255) NOT NULL,
                        `login` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `role_id` INT NOT NULL,
                        PRIMARY KEY (`id`)
);

CREATE TABLE dits.`Role` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) NOT NULL UNIQUE,
                        PRIMARY KEY (`id`)
);

ALTER TABLE `Answer` ADD CONSTRAINT `Answer_fk0` FOREIGN KEY (`question_id`) REFERENCES `Question`(`id`);

ALTER TABLE `Question` ADD CONSTRAINT `Question_fk0` FOREIGN KEY (`test_id`) REFERENCES `Test`(`id`);

ALTER TABLE `Literature` ADD CONSTRAINT `Literature_fk0` FOREIGN KEY (`question_id`) REFERENCES `Question`(`id`);

ALTER TABLE `Link` ADD CONSTRAINT `Link_fk0` FOREIGN KEY (`literature_id`) REFERENCES `Literature`(`id`);

ALTER TABLE `Test` ADD CONSTRAINT `Test_fk0` FOREIGN KEY (`topic_id`) REFERENCES `Topic`(`id`);

ALTER TABLE `Statistic` ADD CONSTRAINT `Statistic_fk0` FOREIGN KEY (`question_id`) REFERENCES `Question`(`id`);

ALTER TABLE `Statistic` ADD CONSTRAINT `Statistic_fk1` FOREIGN KEY (`user_id`) REFERENCES `User`(`id`);

ALTER TABLE `User` ADD CONSTRAINT `User_fk0` FOREIGN KEY (`role_id`) REFERENCES `Role`(`id`);


INSERT INTO dits.`Role` (role_name) VALUES ('user'), ('tutor'), ('admin');

INSERT INTO dits.`User` (first_name, middle_name, last_name, login, password, email, role_id) VALUES
('Alexandr', 'Alexandrovich', 'Heilash', 'Alexandr_H', 'Ah123456', 'alexandr.heilash@gmail.com', 3),
('Yahor', 'Sergeevich', 'Radziuk', 'Yahor_R', 'Yr123456', 'yahor.radziuk@gmail.com', 1),
('Aleksandr', 'Dmitrievich', 'Anashkevich', 'Aleksandr_A', 'Aa123456', 'aleksandr.anashkevich@gmail.com', 2);

INSERT INTO dits.`Topic` (description, name) VALUES
('Исключение и ошибки. Checked и Unchecked исключение. Проверка исключений.','Исключения'),
('Примитивные типы. Группы примитивов. Особенности.','Примитивные типы'),
('Класс String. Классы StringBuilder и StringBuffer. Методы класса.','Строки'),
('Ссылочные типы. Оссобенности. Класс Object.','Ссылочные типы'),
('ООП. Инкапсуляция. Наследование. Полиморфизм.','ООП');

INSERT INTO dits.`Test` (name, description, topic_id) VALUES
('Test 1','Тест по Исключениям', 1),
('Test 1','Тест по примитивам', 2),
('Test 1','Тест по строкам', 3),
('Test 1','Тест по ссылочным типам', 4),
('Test 1','Тест по ООП', 5);

INSERT INTO dits.`Question` (description, test_id) VALUES
('Что из приведенного ниже `является допустимым способом создания массива в java?', NULL),
('Сколько примитивных типов данных есть в Java?', NULL),
('Что из нижеперечисленного непроверяемые (unchecked) исключения в джава?', 1),
('Что такое ООП?', 1),
('Что такое класс в Java?', 1),
('Как объявить класс в коде?', 1),
('Для чего используется оператор NEW?', 1),
('Что означает ключевое слово extends?', 1),
('Какое из следующих утверждений верно для класса: java.util.ArrayList?', 1),
('Какое из следующих утверждений верно для method-local inner class?', 1),
('Как указать индекс последнего элемента массива?', 1),
('Что такое --х?', 1),
('Выберите правильный вариант записи операции сравнения?', NULL),
('Какой модификатор доступа необходимо использовать, чтобы переменная была видна только в текущем классе?', NULL),
('Какой модификатор доступа необходимо использовать, чтобы переменная была видна везде?', NULL);

INSERT INTO dits.`Answer` (description, correct, question_id) VALUES
('int e [] = new int{2, 3, 1}', 0, 1),
('int d [] = new int(10)', 0, 1),
('int c [] = (1, 3, 2)', 0, 1),
('int a [] = {1, 3, 2}', 1, 1),
('6', 0, 2),
('7', 0, 2),
('8', 1, 2),
('9', 0, 2),
('NullPointerException', 0, 3),
('Exception', 0, 3),
('RuntimeException', 1, 3),
('FileNotFoundException', 0, 3),
('Объектно-ориентированное программирование — методология программирования, основанная на представлении программы в виде совокупности объектов, каждый из которых является экземпляром определенного класса, а классы образуют иерархию наследования.', 1, 4),
('Объектно-ориентированное программирование — так называют любой тип программирования, в котором используются понятия высокого уровня и, в отличие от Assembler, в котором не работают напрямую с ячейками памяти ПК.', 0, 4),
('Объектно-ориентированное программирование — просто красивое понятие. Если вдуматься, оно не несет дополнительной смысловой нагрузки, просто программисты любят аббревиатуры, так области их знаний выглядят сложнее.', 0, 4),
('Очень одинокий программист.', 0, 4),
('Уровень сложности программы. Все операторы делятся на классы в зависимости от сложности их использования.', 0, 5),
('Базовым элементом объектно-ориентирован­ного программирования в языке Java.', 1, 5),
('Просто одно из возможных названий переменной.', 0, 5),
('Такое понятие есть только в C++, в Java такого понятия нет.', 0, 5),
('class MyClass {}', 1, 6),
('new class MyClass {}', 0, 6),
('select * from class MyClass {}', 0, 6),
('MyClass extends class {}', 0, 6),
('Для создания новой переменной.', 0, 7),
('Для объявления нового класса.', 0, 7),
('Для создания экземпляра класса.', 1, 7),
('Это антагонист оператора OLD.', 0, 7),
('Что данные класс наследуется от другого.', 1, 8),
('Что это дополнительный модуль класса, который расширяет его свойства.', 0, 8),
('Что два класса делают одно и то же.', 0, 8),
('Что это самый большой класс в программе.', 0, 8),
('Элементы в коллекции упорядочены.', 0, 9),
('Элементы коллекции гарантированно уникальные.', 0, 9),
('Коллекция гарантированно неизменная (immutable).', 0, 9),
('Элементы в коллекции доступны с помощью уникального ключа.', 1, 9),
('Должен быть объявлен как final.', 0, 10),
('Может быть объявлен как static.', 1, 10),
('Может быть объявлен как public.', 0, 10),
('Может быть объявлен как finalabstract.', 0, 10),
('array.length-1', 1, 11),
('array.size-1', 0, 11),
('array.size', 0, 11),
('array.length', 0, 11),
('постфиксный декремент', 0, 12),
('префиксный декремент', 1, 12),
('постфиксный инкремент', 0, 12),
('префиксный инкремент', 0, 12),
('0=<x', 0, 13),
('x=>0', 0, 13),
('x=0;', 0, 13),
('0<=x', 1, 13),
('protected', 0, 14),
('private', 1, 14),
('default (package visible)', 0, 14),
('public', 0, 14),
('default (package visible)', 0, 15),
('private', 0, 15),
('public', 1, 15),
('protected', 0, 15);

INSERT INTO dits.`Literature` (`description`, `question_id`) VALUES
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 1),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 2),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 3),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 4),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 5),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 6),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 7),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 8),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 9),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 10),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 11),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 12),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 13),
('Хорстманн К.С. - Java SE 8. Базовый курс - 2015.', 14),
('Герберт Шилдт Java 8. Полное руководство 9 издание.', 15);

INSERT INTO dits.`Link` (`link`, `literature_id`) VALUES
('https://metanit.com/java/tutorial/2.4.php', 1),
('https://metanit.com/java/tutorial/2.12.php', 2),
('https://metanit.com/java/tutorial/4.2.php', 3),
('https://vertex-academy.com/tutorials/ru/chto-takoe-oop/', 4),
('https://metanit.com/java/tutorial/3.1.php', 5),
('https://metanit.com/java/tutorial/3.12.php', 6),
('https://metanit.com/java/tutorial/3.1.php', 7),
('https://metanit.com/java/tutorial/3.5.php', 8),
('https://metanit.com/java/tutorial/5.2.php', 9),
('https://metanit.com/java/tutorial/3.12.php', 10),
('https://metanit.com/java/tutorial/2.4.php', 11),
('https://metanit.com/java/tutorial/2.3.php', 12),
('https://metanit.com/java/tutorial/2.14.php', 13),
('https://metanit.com/java/tutorial/3.3.php', 14),
('https://metanit.com/java/tutorial/3.3.php', 15);

INSERT INTO dits.`Statistic` (`date`, `correct`, `question_id`, `user_id`) VALUES
('2020-07-04', 1, 3, 1),
('2020-07-04', 1, 4, 1),
('2020-07-04', 0, 5, 1),
('2020-07-04', 0, 6, 1),
('2020-07-04', 1, 7, 1),
('2020-07-04', 1, 8, 1),
('2020-07-04', 1, 9, 1),
('2020-07-04', 1, 10, 1),
('2020-07-04', 1, 11, 1),
('2020-07-04', 0, 12, 1);
