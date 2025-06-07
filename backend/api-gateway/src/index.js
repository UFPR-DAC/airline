const express = require('express');
const cors = require('cors');
const morgan = require('morgan');
const routes = require('./routes');

// Inicializa a aplicação Express
const app = express();

// Middlewares Globais
app.use(cors()); // Habilita o Cross-Origin Resource Sharing
app.use(express.json()); // Permite que o express entenda requisições com corpo em JSON
app.use(morgan('dev')); // Middleware para logar as requisições HTTP no console

// Define a porta do servidor
const PORT = process.env.PORT || 8080;

// Carrega as rotas do gateway
app.use(routes);

// Inicia o servidor
app.listen(PORT, () => {
    console.log(`API Gateway em execução na porta ${PORT}`);
});