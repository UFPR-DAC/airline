import express from 'express';

import cors from 'cors';
import funcionarioRouter from "./routes/funcionario.routes.js";
import authRoute from "./routes/auth.routes.js";
import clienteRouter from "./routes/cliente.routes.js";
const PORT = process.env.PORT || 3000;
const app = express();


app.use(cors()); // Habilita o Cross-Origin Resource Sharing
app.use(express.json()); // Permite que o express entenda requisições com corpo em JSON

// Define a porta do servidor

// Carrega as rotas do gateway
app.use('/funcionarios', funcionarioRouter);
app.use('/clientes', clienteRouter);
app.use('/', authRoute);

app.use((_req, res) => {
    res.status(404).json({
        code: 404,
        status: 'Erro',
        message: 'Esta rota não existe',
        data: null
    })
})

// Inicia o servidor
app.listen(PORT, () => {
    console.log(`API gateway rodando na porta ${PORT}`);
});