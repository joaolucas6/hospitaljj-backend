CREATE TABLE `tb_user` (
  `tipo_user` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `genero` enum('FEMININO','MASCULINO','OUTRO') DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `numero_telefone` varchar(11) DEFAULT NULL,
  `role` enum('ADMIN','MEDICO','PACIENTE') DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `sobrenome` varchar(50) DEFAULT NULL,
  `numero_registro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
