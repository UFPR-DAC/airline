import express from "express";
import {SERVICE_CONFIG} from "../config/services.js";
import axios from "axios";

const clienteRouter = express.Router();

clienteRouter.post('/', async(req, res) => {
    const {...dadosCliente } = req.body;

    try {
        console.log('tentando cliente service endpoint /clientes...')
        const response = await axios.post(`${SERVICE_CONFIG.CLIENTE.url}`, dadosCliente);
        const cliente = response.data;
        console.log('tentando auth service endpoint /cadastro...');
        await axios.post(`${SERVICE_CONFIG.AUTH.url}/cadastro`, {
            login: cliente.email,
            tipo: "CLIENTE"
        });
        return res.status(201).json(cliente)
    } catch (e) {
        console.error("Erro ao criar cliente. ", e.response?.data || e.message);
        return res.status(e.response?.status || 500).json({
            message: e.response?.data?.message || "Erro ao criar cliente."
        })
    }
})

clienteRouter.get('/', async(req, res) => {
    try {
        const response = await axios.get(`${SERVICE_CONFIG.CLIENTE.url}`);
        res.status(response.status).json(response.data);
    } catch (e) {
        console.error("Erro ao buscar lista de clientes. ", e.response?.data || e.message);
        res.status(e.response?.status || 500).json({
            message: e.response?.data?.message || "Erro ao buscar lista de clientes"
        })
    }
})

export default clienteRouter;