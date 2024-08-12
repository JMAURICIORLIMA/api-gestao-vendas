CREATE TABLE categoria (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO categoria (nome) values ('Tecnologia');
INSERT INTO categoria (nome) values ('Acessórios para veículos');
INSERT INTO categoria (nome) values ('Esporte e Lazer');
INSERT INTO categoria (nome) values ('Casa e Eletrodomésticos');
INSERT INTO categoria (nome) values ('Joias e Relógios');