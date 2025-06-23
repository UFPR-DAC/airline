import {Router} from "express";
import { createProxyMiddleware } from 'http-proxy-middleware';
import autenticarEAutorizar from '../middleware/authMiddleware.js';

const router = Router();

// URL base do microsserviço de funcionários
// Em um ambiente com Docker, seria o nome do serviço (ex: 'http://ms-funcionario:8082')
const FUNCIONARIO_SERVICE_URL = 'http://localhost:8084';

// Permissão necessária para acessar as rotas de funcionários
const PERMISSAO_FUNCIONARIO = ['FUNCIONARIO'];

// Configuração do Proxy para o Microsserviço de Funcionários
const funcionarioProxy = createProxyMiddleware({
    target: FUNCIONARIO_SERVICE_URL,
    changeOrigin: true, // Necessário para o microsserviço receber a requisição corretamente
    pathRewrite: {
        '^/funcionarios': '/funcionarios', // Reescreve a URL para o alvo
    },
});

// Aplica o middleware de autenticação e autorização em todas as rotas de /funcionarios
// E em seguida, aplica o proxy para encaminhar a requisição
router.use('/funcionarios', autenticarEAutorizar(PERMISSAO_FUNCIONARIO), funcionarioProxy);

// --- Outras Rotas ---
// Aqui você adicionaria as rotas para os outros microsserviços (Cliente, Voo, etc.)
// Exemplo para um microsserviço de clientes que não exige autenticação para o cadastro
/*
const CLIENTE_SERVICE_URL = 'http://localhost:8081';
const clienteProxy = createProxyMiddleware({ target: CLIENTE_SERVICE_URL, changeOrigin: true });
router.post('/clientes', clienteProxy); // Rota pública de autocadastro
*/


export default router;