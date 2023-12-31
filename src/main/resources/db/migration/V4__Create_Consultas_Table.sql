CREATE TABLE `tb_consultas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_de_criacao` datetime(6) DEFAULT NULL,
  `horario_inicio` datetime(6) DEFAULT NULL,
  `horario_termino` datetime(6) DEFAULT NULL,
  `observacoes_medica` varchar(1000) DEFAULT NULL,
  `preco` decimal(38,2) DEFAULT NULL,
  `receita_medica` varchar(1000) DEFAULT NULL,
  `status` enum('ACEITO','CANCELADO','CONCLUIDO','CONFIRMADO','NEGADO','PENDENTE') DEFAULT NULL,
  `especialidade_id` bigint DEFAULT NULL,
  `medico_id` bigint DEFAULT NULL,
  `paciente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK95rcucdw4l30n4e1c35c3dpok` (`especialidade_id`),
  KEY `FK4j5ale6kyngari0542a04ja25` (`medico_id`),
  KEY `FKdsh9itl8oth3p5gl61kh3lfxs` (`paciente_id`),
  CONSTRAINT `FK4j5ale6kyngari0542a04ja25` FOREIGN KEY (`medico_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK95rcucdw4l30n4e1c35c3dpok` FOREIGN KEY (`especialidade_id`) REFERENCES `tb_especialidades` (`id`),
  CONSTRAINT `FKdsh9itl8oth3p5gl61kh3lfxs` FOREIGN KEY (`paciente_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
