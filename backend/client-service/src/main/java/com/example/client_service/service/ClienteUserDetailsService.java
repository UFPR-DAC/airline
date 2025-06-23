//package com.example.client_service.service;
//
//import com.example.client_service.model.Cliente;
//import com.example.client_service.repository.ClienteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ClienteUserDetailsService implements UserDetailsService {
//    @Autowired
//    private final ClienteRepository clienteRepository;
//
//    public ClienteUserDetailsService(ClienteRepository clienteRepository) {
//        this.clienteRepository = clienteRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Cliente cliente = clienteRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(cliente.getCpf())
//                .authorities("CLIENTE")
//                .build();
//
//    }
//}
