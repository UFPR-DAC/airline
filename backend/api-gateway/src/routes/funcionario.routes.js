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

export default funcionarioRouter;