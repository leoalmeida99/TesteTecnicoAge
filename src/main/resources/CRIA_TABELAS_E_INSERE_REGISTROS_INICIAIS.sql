CREATE TABLE exame (
	rowid bigint PRIMARY KEY auto_increment, 
	nm_exame VARCHAR(255)
);

INSERT INTO exame (nm_exame) VALUES 
('Acuidade Visual'),
('Urina'), 
('Clinico'), 
('Sangue');

CREATE TABLE funcionario (
	rowid bigint PRIMARY KEY auto_increment, 
	nm_funcionario VARCHAR(255)
);


INSERT INTO funcionario (nm_funcionario) VALUES 
('João Augusto'),
('Leonardo Almeida'), 
('Douglas Gonçalves'), 
('Lucas Souza');

CREATE TABLE exame_realizado (
  rowid bigint PRIMARY KEY auto_increment,
  rowid_funcionario bigint,
  rowid_exame bigint,
  dt_realizacao date
);
INSERT INTO exame_realizado (rowid_funcionario, rowid_exame, dt_realizacao) VALUES 
(1, 2, '2023-03-07'),
(2, 1, '2022-10-10'),
(3, 3, '2005-09-09'),
(1, 1, '2023-07-01'),
(3, 1, '2021-12-12'),
(2, 3, '2022-05-02'),
(3, 4, '2023-06-24'),
(3, 1, '2020-11-06'),
(4, 3, '2022-08-27'),
(1, 2, '2021-10-24'),
(2, 3, '2023-12-09'),
(1, 3, '2020-10-11');





