CREATE TABLE produto (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_custo DECIMAL(10,2) NOT NULL,
    preco_venda DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(500),
    codigo_categoria BIGINT NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
);

-- Inserções
INSERT INTO produto (descricao, quantidade, preco_custo, preco_venda, observacao, codigo_categoria) VALUES
('TV Philco 32"', 10, 750.00, 1200.00, 'TV 32 Polegadas Philco LED HD Conv. Digital PTV32B51D', 1),
('AOC Smart TV 43"', 10, 1400.00, 1900.00, 'Smart TV LED 43 Polegadas AOC LE43S5970S Full HD Wi-Fi 2 USB', 1),
('Samsung HD TV 32"', 10, 800.00, 1150.00, 'TV LED 32 Samsung UN32N4000', 1),
('Celular Moto G7 Plus', 25, 1350.00, 2100.00, 'Celular Motorola Moto G7 Plus Indigo 64GB 4GB RAM XT1965', 1),
('Smartphone Samsung Galaxy S9', 15, 3100.00, 3800.00, 'Smartphone Samsung Galaxy S9+ Tela 6.2 128GB 6GB de RAM', 1),
('Piscina 10 mil litros', 10, 650.00, 850.00, 'Piscina 10 mil litros', 3),
('Piscina 20 mil litros', 10, 800.00, 1100.00, 'Piscina 20 mil litros', 3),
('Piscina 30 mil litros', 10, 950.00, 1300.00, 'Piscina 30 mil litros', 3),
('Liquidificador 12 Veloc. 1000w', 30, 90.00, 180.00, 'Liquidificador 12 Veloc. 1000w Turbo Premium Mondial 110V', 4),
('Fogão Elétrico De Mesa', 8, 75.00, 140.00, 'Fogão Elétrico De Mesa 2 Pratos 2000 Watts Agrato 220V', 4),
('Fogão 4 Bocas', 20, 300.00, 450.00, 'Fogão 4 Bocas Atlas Mônaco Com Acendimento Automático', 4),
('Fogão 5 Bocas', 30, 900.00, 1300.00, 'Fogão 5 Bocas Tripla Chama Automático Agile Glass Inox Atlas', 4),
('Cafeteira Expresso', 22, 340.00, 450.00, 'Cafeteira Expresso 15 Bar Coffee Cream 110V Mondial', 4),
('Geladeira / Refrigerador Electrolux', 50, 1150.00, 1700.00, 'Geladeira / Refrigerador Electrolux 240 Litros 1 Porta Class', 4),
('Relógio Technos Masculino Dourado', 35, 250.00, 330.00, 'Relógio Technos Masculino Dourado Performer - 2115LAA/4C', 5),
('Relógio Nibosi Cronógrafo', 50, 130.00, 200.00, 'Relógio Nibosi Cronógrafo Fr Grátis Promoção Carnaval', 5),
('Anel Maciço Ouro 18k', 60, 320.00, 450.00, 'Anel Solitário Maciço Ouro 18k 750 7mm Escolha A Cor', 5);
