import express from "express";
import axios from "axios";
import {SERVICE_CONFIG} from "../config/services.js";

const funcionarioRouter = express.Router();

funcionarioRouter.post('/', async (req, res) => {
    const {...dadosFuncionario } = req.body;

    try {
        console.log('tentando funcionario service endpoint /funcionarios...')
        const response = await axios.post(
            `${SERVICE_CONFIG.FUNCIONARIO.url}`,
            dadosFuncionario
        )
        const funcionario = response.data;
        await axios.post(`${SERVICE_CONFIG.AUTH.url}/cadastro`, {
            login: funcionario.email,
            tipo: "FUNCIONARIO"
        });
        return res.status(201).json(funcionario);
    } catch (e) {
        console.error("Erro ao criar funcionário. ", e.response?.data || e.message);
        return res.status(e.response?.status || 500).json({
            message: e.response?.data?.message || "Erro ao criar funcionário."
        })
    }
});

funcionarioRouter.get('/', async(req, res) => {
    try {
        const response = await axios.get(`${SERVICE_CONFIG.FUNCIONARIO.url}`);
        res.status(response.status).json(response.data);
    } catch (e) {
        console.error("Erro ao buscar lista de funcionários. ", e.response?.data || e.message);
        res.status(e.response?.status || 500).json({
            message: e.response?.data?.message || "Erro ao buscar lista de funcionários"
        })
    }
    })

funcionarioRouter.put('/:codigoFuncionario', async (req, res) => {
    const { codigoFuncionario } = req.params;
    try {
        const response = await axios.put(`${SERVICE_CONFIG.FUNCIONARIO.url}`,
            req.body)
        res.status(200).json(response.data);
    } catch (e) {
        console.error("Erro ao atualizar funcionário. ", e.response?.data || e.message);
        const status = e.response?.status || 500;
        if (status === 401) {
            return res.status(401).json({ message: "Usuário não autenticado."})
        }
        if (status === 403) {
            return res.status(403).json({ message: "Acesso não permitido."})
        }
        if (status === 404) {
            return res.status(404).json({ message: "Funcionário não encontrado."})
        }
        return res.status(500).json({ message: "Erro interno ao atualizar funcionário." });
    }
})

export default funcionarioRouter;