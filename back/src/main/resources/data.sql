INSERT IGNORE INTO `user` (id, email, user_name, password)
VALUES (1, 'joe@gmail.com','jojo','$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq'),
       (2, 'david@gmail.com','david','$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq'),
       (3, 'christophe@gmail.com','cricri','$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq');

INSERT IGNORE INTO `topic` (id, title,  description)
VALUES (1, 'Java', 'Java language'),
       (2, 'Python', 'Python language'),
       (3, 'Javascript', 'Javascript language'),
       (4, 'C', 'C language');

INSERT IGNORE INTO `subscription` (user_id, topic_id)
VALUES (1,1),
       (1,2),
       (1,3),
       (1,4),
       (2,1);

INSERT IGNORE INTO `post` (id, title, description, created_at, user_id, topic_id)
VALUES (1, 'Java POO', 'Java POO written by jojo', '2025-01-01', 1, 1),
       (2, 'Java other', 'Java POO other arti written by david', '2025-01-01',2,1),
       (3, 'Python', 'Python written by jojo', '2025-01-01', 1, 2);

INSERT IGNORE INTO `comment` (id, content, created_at, user_id, post_id)
VALUES (1, 'first comment for Java POO article', '2025-01-01', 1, 1),
       (2, 'second comment for Java POO article', '2025-01-01', 3, 1);
