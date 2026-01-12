INSERT INTO patient (id, name, birth_date, email, gender, created_at)
VALUES
(1, 'Rahul Sharma', '1998-04-12', 'rahul.sharma@gmail.com', 'A_POSITIVE', NOW());

INSERT INTO patient (id, name, birth_date, email, gender, created_at)
VALUES
(2, 'Ananya Verma', '2001-09-25', 'ananya.verma@gmail.com', 'B_POSITIVE', NOW());

INSERT INTO patient (id, name, birth_date, email, gender, created_at)
VALUES
(3, 'Amit Singh', '1995-01-18', 'amit.singh@gmail.com', 'O_POSITIVE', NOW());

INSERT INTO patient (id, name, birth_date, email, gender, created_at)
VALUES
(4, 'Sneha Patel', '1999-06-30', 'sneha.patel@gmail.com', 'AB_POSITIVE', NOW());

INSERT INTO patient (id, name, birth_date, email, gender, created_at)
VALUES
(5, 'Karan Mehta', '1993-11-05', 'karan.mehta@gmail.com', 'A_NEGATIVE', NOW());

INSERT INTO patient (id, name, birth_date, email, gender, created_at)
VALUES
(6, 'Pooja Nair', '2002-03-14', 'pooja.nair@gmail.com', 'O_NEGATIVE', NOW());

INSERT INTO doctor (name, specialization, email)
VALUES
  ('Dr. Amit Sharma', 'Cardiology', 'amit.sharma@hospital.com'),
  ('Dr. Neha Verma', 'Neurology', 'neha.verma@hospital.com'),
  ('Dr. Rajesh Kumar', 'Orthopedics', 'rajesh.kumar@hospital.com'),
  ('Dr. Priya Singh', 'Pediatrics', 'priya.singh@hospital.com'),
  ('Dr. Anil Mehta', 'Dermatology', 'anil.mehta@hospital.com');
