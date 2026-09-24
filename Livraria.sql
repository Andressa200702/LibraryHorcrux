create database senacDB;
use senacDB;

CREATE TABLE livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    isbn VARCHAR(20),
    ano_publicacao INT,
    preco DOUBLE
);

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    cpf VARCHAR(14),
    telefone VARCHAR(20)
);

CREATE TABLE venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    livro_id BIGINT,
    cliente_id BIGINT,
    quantidade INT,
    valor_total DOUBLE,
    data_venda DATE,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

SELECT * FROM LIVRO;
select * from cliente;
select * from venda;

ALTER TABLE livro ADD COLUMN estoque INT DEFAULT 10;
UPDATE livro SET estoque = 10 WHERE estoque IS NULL;