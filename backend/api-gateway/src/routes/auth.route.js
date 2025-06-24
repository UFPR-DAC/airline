import express from 'express';
import axios from 'axios';
import { SERVICE_CONFIG } from '../config/services.js';

const router = express.Router();

router.post('/login', async (req, res) => {
    try {
        const authResponse = await axios.post(`${SERVICE_CONFIG.AUTH.url}/login`, req.body);
        console.log(authResponse)
        const { token, tipo } = authResponse.data;
        const { userId } = authResponse.data?.usuario;

        let userResponse;

        /*if (tipo === 'cliente') {
            userResponse = await axios.get(`${SERVICE_CONFIG.CLIENT.url}/${userId}`);
        } else if (tipo === "FUNCIONARIO") {
            userResponse = await axios.get(`${SERVICE_CONFIG.EMPLOYEE.url}/${userId}`);
        } else {
            return res.status(400).json({ message: 'Tipo de usuário inválido.' });
        }

        if (userResponse.status !== 200) {
            return res.status(userResponse.status).json(userResponse.data);
        }

        authResponse.data.usuario = userResponse.data;
        */
        return res.status(200).json({
            ...authResponse.data,
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
        console.log('Logout request received:', req.body);
        const response = await axios.get(
            `${SERVICE_CONFIG.AUTH.url}/logout`,
            { headers: { Authorization: `Bearer ${req.body.token}` } }
        );

        return res.status(200).json({ message: 'Logout realizado com sucesso' });
    } catch (e) {
        console.error('Erro no logout:', e.response?.data || e.message);
        return res.status(e.response?.status || 500).json(
            e.response?.data || { message: 'Erro interno durante o logout.' }
        );
    }
});

export default router;