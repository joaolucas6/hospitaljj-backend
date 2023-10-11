package com.joaolucas.hospitalJJ.utils;

import com.joaolucas.hospitalJJ.models.dto.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class ValidacaoDeDados {

    public static boolean validarDadosDoUser(MedicoDTO medicoDTO){
        if(todosOsAtributosSaoNulos(medicoDTO)) return false;
        if(medicoDTO.getNome() != null && medicoDTO.getNome().isBlank() || medicoDTO.getNome().length() > 50) return false;
        if(medicoDTO.getSobrenome() != null && medicoDTO.getSobrenome().isBlank() || medicoDTO.getSobrenome() != null && medicoDTO.getSobrenome().length() > 50) return false;
        if(medicoDTO.getEmail() != null && !validarEmail(medicoDTO.getEmail())) return false;
        if(medicoDTO.getCpf() != null && !validarCpf(medicoDTO.getCpf())) return false;
        if(medicoDTO.getDataNascimento() != null && medicoDTO.getDataNascimento().isAfter(LocalDate.now())) return false;
        if(medicoDTO.getNumeroTelefone() != null && medicoDTO.getNumeroTelefone().length() != 9 || medicoDTO.getNumeroTelefone() != null &&  medicoDTO.getNumeroTelefone().isBlank()) return false;
        return true;
    }

    public static boolean validarDadosDoUser(PacienteDTO pacienteDTO){
        if(todosOsAtributosSaoNulos(pacienteDTO)) return false;
        if(pacienteDTO.getNome() != null && pacienteDTO.getNome().isBlank() || pacienteDTO.getNome().length() > 50) return false;
        if(pacienteDTO.getSobrenome() != null && pacienteDTO.getSobrenome().isBlank() || pacienteDTO.getSobrenome() != null && pacienteDTO.getSobrenome().length() > 50) return false;
        if(pacienteDTO.getEmail() != null && !validarEmail(pacienteDTO.getEmail())) return false;
        if(pacienteDTO.getCpf() != null && !validarCpf(pacienteDTO.getCpf())) return false;
        if(pacienteDTO.getDataNascimento() != null && pacienteDTO.getDataNascimento().isAfter(LocalDate.now())) return false;
        if(pacienteDTO.getNumeroTelefone() != null && pacienteDTO.getNumeroTelefone().length() != 9 || pacienteDTO.getNumeroTelefone() != null &&  pacienteDTO.getNumeroTelefone().isBlank()) return false;
        return true;
    }

    public static boolean validarDadosDoUser(AdminDTO adminDTO){
        if(todosOsAtributosSaoNulos(adminDTO)) return false;
        if(adminDTO.getNome() != null && adminDTO.getNome().isBlank() || adminDTO.getNome().length() > 50) return false;
        if(adminDTO.getSobrenome() != null && adminDTO.getSobrenome().isBlank() || adminDTO.getSobrenome() != null && adminDTO.getSobrenome().length() > 50) return false;
        if(adminDTO.getEmail() != null && !validarEmail(adminDTO.getEmail())) return false;
        if(adminDTO.getCpf() != null && !validarCpf(adminDTO.getCpf())) return false;
        if(adminDTO.getDataNascimento() != null && adminDTO.getDataNascimento().isAfter(LocalDate.now())) return false;
        if(adminDTO.getNumeroTelefone() != null && adminDTO.getNumeroTelefone().length() != 9 || adminDTO.getNumeroTelefone() != null &&  adminDTO.getNumeroTelefone().isBlank()) return false;
        return true;
    }

    public static boolean validarDadosDaEspecialidade(EspecialidadeDTO especialidadeDTO){
        if(todosOsAtributosSaoNulos(especialidadeDTO)) return false;
        if(especialidadeDTO.getNome() != null && especialidadeDTO.getNome().isBlank() || especialidadeDTO.getNome() != null && especialidadeDTO.getNome().length() > 50) return false;
        if(especialidadeDTO.getDescricao() != null && especialidadeDTO.getDescricao().isBlank() || especialidadeDTO.getDescricao() != null && especialidadeDTO.getDescricao().length() > 500) return false;
        return true;
    }

    public static boolean validarDadosDaConsulta(ConsultaDTO consultaDTO){
        if(todosOsAtributosSaoNulos(consultaDTO)) return false;
        if(consultaDTO.getPreco() != null && consultaDTO.getPreco().compareTo(BigDecimal.ZERO) == -1) return false;
        if(consultaDTO.getReceitaMedica() != null && consultaDTO.getReceitaMedica().isBlank() || consultaDTO.getReceitaMedica() != null && consultaDTO.getReceitaMedica().length() > 1000) return false;
        if(consultaDTO.getObservacoesMedica() != null && consultaDTO.getObservacoesMedica().isBlank() || consultaDTO.getObservacoesMedica() != null && consultaDTO.getObservacoesMedica().length() > 1000) return false;
        if(consultaDTO.getHorarioInicio() != null && consultaDTO.getHorarioTermino() != null && consultaDTO.getHorarioInicio().isAfter(consultaDTO.getHorarioTermino())) return false;
        return true;
    }

    public static boolean validarDadosDaSolicitacaoDeConsulta(SolicitacaoConsultaDTO solicitacaoConsultaDTO){
        if(todosOsAtributosSaoNulos(solicitacaoConsultaDTO)) return false;
        if(solicitacaoConsultaDTO.getMotivoDaConsulta() != null && solicitacaoConsultaDTO.getMotivoDaConsulta().isBlank() || solicitacaoConsultaDTO.getMotivoDaConsulta() != null && solicitacaoConsultaDTO.getMotivoDaConsulta().length() > 500) return false;

        if(solicitacaoConsultaDTO.getInicioDaConsulta() != null && solicitacaoConsultaDTO.getInicioDaConsulta().isBefore(LocalDateTime.now()) || solicitacaoConsultaDTO.getFimDaConsulta() != null && solicitacaoConsultaDTO.getFimDaConsulta().isBefore(LocalDateTime.now())) return false;
        if(solicitacaoConsultaDTO.getInicioDaConsulta() != null && solicitacaoConsultaDTO.getFimDaConsulta() != null && solicitacaoConsultaDTO.getInicioDaConsulta().isAfter(solicitacaoConsultaDTO.getFimDaConsulta())) return false;

        return true;
    }

    public static boolean validarCpf(String valor){
        String cpf = valor.replace(".", "").replace("-", "");

        String primeiraParte = cpf.substring(0, 9);

        int resultadoDaPrimeiraParte = 0;

        for(int i = 10, j = 0; i >= 2; i--, j++){
            int numero = Integer.parseInt(String.valueOf(primeiraParte.charAt(j)));

            numero *= i;
            resultadoDaPrimeiraParte += numero;
        }

        if(resultadoDaPrimeiraParte * 10 % 11 !=  Integer.parseInt(String.valueOf(cpf.charAt(9)))) return false;

        int resultadoDaSegundaParte = 0;

        for(int i = 11, j = 0; i >= 2; i--, j++){

            int numero = Integer.parseInt(String.valueOf(cpf.charAt(j)));

            numero *= i;
            resultadoDaSegundaParte += numero;
        }

        if(resultadoDaSegundaParte * 10 % 11 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) return false;

        return true;
    }

    public static boolean validarEmail(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return pattern.matcher(email).matches();
    }

    public static boolean todosOsAtributosSaoNulos(Object object){
        Class<?> objectClass = object.getClass();
        for(Field field : objectClass.getFields()){
            if(field == null) return true;
        }

        return false;
    }
}
