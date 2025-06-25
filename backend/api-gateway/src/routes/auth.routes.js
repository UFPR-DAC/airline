import express from 'express';
import axios from 'axios';
import { SERVICE_CONFIG } from '../config/services.js';

const router = express.Router();

router.get('/internal/usuario', async (req, res) => {
    const { email } = req.query;
    if (!email) return res.status(400).json({ error: 'E-mail é obrigatório.' });

    try {
        const clienteResponse = await axios.get(`${SERVICE_CONFIG.CLIENTE.url}/busca-email/${email}`);
        return res.json({ tipo: 'CLIENTE', ...clienteResponse.data });
    } catch (err1) {
        try {
            const funcionarioResponse = await axios.get(`${SERVICE_CONFIG.FUNCIONARIO.url}/busca-email/${email}`);
            return res.json({ tipo: 'FUNCIONARIO', ...funcionarioResponse.data });
        } catch (err2) {
            return res.status(404).json({ error: 'Usuário não encontrado em nenhum banco de dados.' });
        }
    }
});

router.post('/login', async (req, res) => {
    try {
        console.log("Chamado o endpoint login no gateway!", JSON.stringify(req.body));
        const authResponse = await axios.post(`${SERVICE_CONFIG.AUTH.url}/login`, req.body);
        console.log("auth response: ", JSON.stringify(authResponse.data, null, 2));
        const { access_token, token_type, tipo, usuario } = authResponse.data;
        const email = usuario?.email;

        if (!usuario) {
            console.error("Resposta do auth service não contém 'usuario' ou 'email':", authResponse.data);
            return res.status(500).json({ message: "Falha ao obter e-mail do usuário autenticado." });
        }

        let userResponse;

        if (tipo === 'CLIENTE') {
            console.log("buscando cliente " + email);
            userResponse = await axios.get(`${SERVICE_CONFIG.CLIENTE.url}/busca-email/${encodeURIComponent(email)}`);
        } else if (tipo === "FUNCIONARIO") {
            console.log("buscando funcionario " + email);
            userResponse = await axios.get(`${SERVICE_CONFIG.FUNCIONARIO.url}/busca-email/${encodeURIComponent(email)}`);
        } else {
            return res.status(400).json({ message: 'Tipo de usuário inválido.' });
        }

        if (userResponse.status !== 200) {
            return res.status(userResponse.status).json(userResponse.data);
        }

        authResponse.data.usuario = userResponse.data;

        return res.status(200).json({
            access_token,
            token_type,
            tipo,
            usuario: userResponse.data
        });

    } catch (e) {
        console.error('Erro no login. ', e.response?.data ?? e);
        return res.status(e.response?.status || 500).json(
            e.response?.data || { message: 'Erro interno ao fazer login.' }
        );
    }
});


router.post('/logout', async (req, res) => {
    try {
        console.log('Realizando logout...', req.body);
        const response = await axios.get(
            `${SERVICE_CONFIG.AUTH.url}/logout`,
            { headers: { Authorization: `Bearer ${req.body.token}` } }
        );

        return res.status(200).json({ message: 'Logout realizado com sucesso' });
    } catch (e) {
        console.error('Erro no logout:', e.response?.data || e.message);
        return res.status(e.response?.status || 500).json(
            e.response?.data || { message: 'Erro interno ao fazer logout.' }
        );
    }
});

export default router;