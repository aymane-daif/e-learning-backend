INSERT INTO instructor(id, name) VALUES (1, 'Aaron Carla'),
                                        (2, 'Billy Abbie'),
                                        (3, 'Birgit Carley'),
                                        (4, 'Birdie Carleen'),
                                        (5, 'Debbie Erna'),
                                        (6, 'Ermelinda Debora'),
                                        (7, 'Karolyn Lucas'),
                                        (8, 'Abdul Phoebe'),
                                        (9, 'Farrah Karoline'),
                                        (10, 'Luanne Philomena'),
                                        (11, 'Karol Luba');

INSERT INTO course(
   id, course_level, price_type, description, last_updated, name, no_students, price, instructor_id
) VALUES
(1, 0, 1, 'Build enterprise level React applications and deploy to production', CURRENT_DATE, 'Complete React Developer', 1000, 250, 1),
(2, 1, 1, 'Learn how to build client-side and fullstack ReactJS apps with NextJS', CURRENT_DATE, 'Next.js & React - The Complete Guide', 500, 300, 1),
(3, 2, 1, 'Learn how to use ReactJS to build real native mobile apps for iOS and Android', CURRENT_DATE, 'React Native - The Practical Guide', 270, 400, 1),
(4, 0, 0, 'Build a real e-commerce app with Angular, Firebase and Bootstrap 4', CURRENT_DATE, 'The Complete Angular Course', 120, 0, 2),
(5, 1, 1, 'A practical example of how to build an application with ASP.NET Core API and Angular from start to finish', CURRENT_DATE, 'Build an app with ASPNET Core and Angular from scratch', 70, 700, 2),
(6, 0, 1, 'Become a professional Python Developer and get hired', CURRENT_DATE, 'Complete Python Developer in 2023', 3000, 120, 3),
(7, 0, 0, 'A Unique Interactive Python Experience', CURRENT_DATE, 'The Modern Python 3 Bootcamp', 3200, 0, 3),
(8, 2, 1, 'Get a kick start on your career and ace your coding interviews!', CURRENT_DATE, 'Python for Data Structures, Algorithms, and Interviews!', 2300, 520, 3),
(9, 1, 1, 'Harness The Power Of Machine Learning For Unsupervised & Supervised Learning In Python', CURRENT_DATE, 'Clustering & Classification With Machine Learning In Python', 2300, 520, 4),
(10, 0, 1, 'Become an Experienced Java Developer with Just One Course', CURRENT_DATE, 'The Complete Java Development Bootcamp', 700, 130, 5);