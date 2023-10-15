package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.models.entities.Medico;
import com.joaolucas.hospitalJJ.models.entities.Paciente;
import com.joaolucas.hospitalJJ.models.records.AuthenticationRequest;
import com.joaolucas.hospitalJJ.models.records.RegisterRequest;
import com.joaolucas.hospitalJJ.repositories.MedicoRepository;
import com.joaolucas.hospitalJJ.repositories.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public String registrarPaciente(RegisterRequest request){
        Paciente paciente = new Paciente();

        paciente.setNome(request.nome());
        paciente.setSobrenome(request.sobrenome());
        paciente.setEmail(request.email());
        paciente.setSenha(passwordEncoder.encode(request.senha()));
        paciente.setCpf(request.cpf());

        return jwtService.gerarToken(pacienteRepository.save(paciente));
    }

    public String registrarMedico(RegisterRequest request){
        Medico medico = new Medico();

        medico.setNome(request.nome());
        medico.setSobrenome(request.sobrenome());
        medico.setEmail(request.email());
        medico.setSenha(passwordEncoder.encode(request.senha()));
        medico.setCpf(request.cpf());

        return jwtService.gerarToken(medicoRepository.save(medico));
    }

    public String autenticar(AuthenticationRequest request){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.email(), request.senha());

        authenticationManager.authenticate(authToken);

        return jwtService.gerarToken(userService.loadUserByUsername(request.email()));
    }


}
