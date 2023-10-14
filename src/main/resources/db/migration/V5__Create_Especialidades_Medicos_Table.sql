CREATE TABLE `tb_especialidades_medicos` (
  `especialidade_id` bigint NOT NULL,
  `medico_id` bigint NOT NULL,
  KEY `FKjff3tgwlaiu016d1lly6cele5` (`medico_id`),
  KEY `FKkmd8vg0w64xr7xa0smwmdducw` (`especialidade_id`),
  CONSTRAINT `FKjff3tgwlaiu016d1lly6cele5` FOREIGN KEY (`medico_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKkmd8vg0w64xr7xa0smwmdducw` FOREIGN KEY (`especialidade_id`) REFERENCES `tb_especialidades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
