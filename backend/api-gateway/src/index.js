import express from 'express';

import cors from 'cors';
import router from "./routes/funcionario.route.js";
import authRoute from "./routes/auth.route.js";
const PORT = process.env.PORT || 3000;
const app = express();


app.use(cors()); // Habilita o Cross-Origin Resource Sharing
app.use(express.json()); // Permite que o express entenda requisições com corpo em JSON

// Define a porta do servidor

// Carrega as rotas do gateway
app.use(router);
app.use(authRoute);

// Inicia o servidor
app.listen(PORT, () => {
    console.log(`API gateway rodando na porta ${PORT}`);
});