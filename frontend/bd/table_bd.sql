create table Endereco (
  id_endereco SERIAL PRIMARY KEY,
  rua varchar(50) not null,
  numero varchar(50) not null,
  cep varchar(50) not null,
  bairro varchar(50) not null,
  cidade varchar(50) not null,
  estado varchar(50) not null,
  complemento varchar(50)
);

create table Usuario (
  id_usuario SERIAL PRIMARY KEY,
  id_endereco int not null,
  email varchar(50) not null,
  senha varchar(50) not null,
  nome varchar(50) not null,
  dataNascimento varchar(50) not null,
  celular varchar(50) not null,
  telefone varchar(50),
  FOREIGN KEY (id_endereco) REFERENCES Endereco (id_endereco) ON DELETE CASCADE
);

create table Aluno (
  id_aluno SERIAL PRIMARY KEY,
  id_usuario int UNIQUE not null,
  FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE CASCADE 
);

create table Personal (
  id_personal SERIAL PRIMARY KEY,
  cref int UNIQUE not null,
  id_usuario int UNIQUE not null,
  FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE CASCADE
);

create table Personal_Aluno (
  id_personal int not null,
  id_aluno int not null,
  PRIMARY key (id_personal, id_aluno),
  FOREIGN KEY (id_personal) REFERENCES Personal (id_personal) ON DELETE cascade,
  FOREIGN KEY (id_aluno) REFERENCES Aluno (id_aluno) ON DELETE cascade
);

CREATE TABLE Avaliacao (
  id_avaliacao SERIAL PRIMARY KEY,
  altura float not null,
  peso float not null,
  femur float not null,
  punho float not null,
  peitoral float not null,
  cintura float not null,
  abdomen float not null,
  quadril float not null,
  coxas float not null,
  panturrilha float not null,
  ombro float not null,
  braco_relaxado float not null,
  braco_contraido float not null,
  antebraco float not null,
  biceps float not null,
  triceps float not null,
  abdominal float not null,
  auxiliarMedio float not null,
  supraIliaca float not null,
  panturrilhaMedial float not null,
  subespular float not null,
  torax float not null,
  coxa float not null 
);

create table Aluno_Avaliacao (
  id_alunoAvaliacao SERIAL PRIMARY KEY,
  id_avaliacao int not null,
  id_aluno int not null,
  data TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  FOREIGN KEY (id_avaliacao) REFERENCES Avaliacao (id_avaliacao) ON DELETE cascade,
  FOREIGN KEY (id_aluno) REFERENCES Aluno (id_aluno) ON DELETE CASCADE
);

create table Anamnese (
  id_anamnese SERIAL PRIMARY KEY,
  id_aluno int unique not null,
  genero varchar(50),
  alergias varchar(50),
  cirurgias varchar(50),
  medicamentos varchar(50),
  vicios varchar(50),
  observacoes varchar(50),
  FOREIGN KEY (id_aluno) REFERENCES Aluno (id_aluno) ON DELETE CASCADE
);

create table Exercicio (
  id_exercicio SERIAL PRIMARY KEY,
  id_personal int not null,
  nome varchar(50) not null,
  categoria varchar(50) not null,
  FOREIGN KEY (id_personal) REFERENCES Personal (id_personal) ON DELETE CASCADE
);

create table Treino (
  id_treino SERIAL PRIMARY KEY,
  id_personal int not null,
  id_aluno int unique not null,
  objetivo varchar(50),
  FOREIGN KEY (id_personal) REFERENCES Personal (id_personal) ON DELETE cascade,
  FOREIGN KEY (id_aluno) REFERENCES Aluno (id_aluno) ON DELETE CASCADE
);

create table Exercicio_Treino (
  id_exercicioTreino SERIAL PRIMARY KEY,
  id_treino int not null,
  id_exercicio int not null,
  divisao varchar(1) not null,
  series int not null,
  repeticoes int not null,
  descricao text,
  FOREIGN KEY (id_treino) REFERENCES Treino (id_treino) ON DELETE cascade,
  FOREIGN KEY (id_exercicio) REFERENCES Exercicio (id_exercicio) ON DELETE cascade
);
