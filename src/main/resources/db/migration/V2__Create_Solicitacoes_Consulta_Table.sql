CREATE TABLE `tb_solicitacoes_consulta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fim_da_consulta` datetime(6) DEFAULT NULL,
  `inicio_da_consulta` datetime(6) DEFAULT NULL,
  `motivo_da_consulta` varchar(500) DEFAULT NULL,
  `status` enum('ACEITO','CANCELADO','CONCLUIDO','CONFIRMADO','NEGADO','PENDENTE') DEFAULT NULL,
  `especialidade_id` bigint DEFAULT NULL,
  `medico_id` bigint DEFAULT NULL,
  `paciente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK82srujvpl4n55vjnyyr1qi1ly` (`especialidade_id`),
  KEY `FKe90lpshtbncfi3mw5kid1og6c` (`medico_id`),
  KEY `FKjjx7oxm3o572aujjtwwl1prng` (`paciente_id`),
  CONSTRAINT `FK82srujvpl4n55vjnyyr1qi1ly` FOREIGN KEY (`especialidade_id`) REFERENCES `tb_especialidades` (`id`),
  CONSTRAINT `FKe90lpshtbncfi3mw5kid1og6c` FOREIGN KEY (`medico_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKjjx7oxm3o572aujjtwwl1prng` FOREIGN KEY (`paciente_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
