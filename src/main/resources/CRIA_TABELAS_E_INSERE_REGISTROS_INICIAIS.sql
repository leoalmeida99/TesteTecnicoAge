CREATE TABLE exame (
	rowid bigint PRIMARY KEY auto_increment, 
	nm_exame VARCHAR(255)
);

INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE funcionario (
	rowid bigint PRIMARY KEY auto_increment, 
	nm_funcionario VARCHAR(255)
);


INSERT INTO funcionario (nm_funcionario) VALUES ('João Augusto'), ('Leonardo Almeida'), ('Douglas Gonçalves'), ('Michael Jackson');

CREATE TABLE exame_realizado (
  rowid bigint PRIMARY KEY auto_increment,
  rowid_funcionario bigint,
  rowid_exame bigint,
  dt_realizacao VARCHAR(255)
);

INSERT INTO exame_realizado (rowid_funcionario, rowid_exame, dt_realizacao)VALUES (1, 2, '2023-03-07');
INSERT INTO exame_realizado (rowid_funcionario, rowid_exame, dt_realizacao)VALUES (2, 1, '2022-10-10');
INSERT INTO exame_realizado (rowid_funcionario, rowid_exame, dt_realizacao)VALUES (3, 3, '2005-9-9');

